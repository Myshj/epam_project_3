package launch.servlets.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ServletService implements BiConsumer<HttpServletRequest, HttpServletResponse> {

    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> commands = new HashMap<>();
    protected final HttpServlet servlet;

    protected void registerCommand(String pattern, BiConsumer<HttpServletRequest, HttpServletResponse> command) {
        commands.putIfAbsent(pattern, command);
    }

    private void addCommandBefore(
            String actionName,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        if (!commands.containsKey(actionName)) {
            return;
        }
        commands.put(actionName, command.andThen(commands.get(actionName)));
    }

    protected final void addCommandBefore(
            List<String> actionNames,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        actionNames.forEach(a -> addCommandBefore(a, command));
    }


    protected ServletService(HttpServlet servlet) {
        this.servlet = servlet;
    }

    @Override
    public final void accept(HttpServletRequest request, HttpServletResponse response) {
        commands.keySet().stream().filter(request.getRequestURI()::matches)
                .map(commands::get)
                .forEach(c -> c.accept(request, response));
    }
}
