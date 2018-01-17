package launch.servlets.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Base class for all servlet services.
 * Maps incoming requests to child services and commands.
 */
public abstract class ServletService implements BiConsumer<HttpServletRequest, HttpServletResponse> {

    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> commands = new HashMap<>();
    protected final HttpServlet servlet;

    /**
     * Starts serving of a given pattern if not serving it already.
     *
     * @param pattern pattern to serve
     * @param command command to serve pattern
     */
    protected void registerCommand(String pattern, BiConsumer<HttpServletRequest, HttpServletResponse> command) {
        commands.putIfAbsent(pattern, command);
    }

    /**
     * TO DELETE
     * Adds command to execute before given action.
     * @param actionName
     * @param command
     */
    private void addCommandBefore(
            String actionName,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        if (!commands.containsKey(actionName)) {
            return;
        }
        commands.put(actionName, command.andThen(commands.get(actionName)));
    }

    /**
     * TO DELETE
     * Adds command to execute before given list of actions
     * @param actionNames
     * @param command
     */
    protected final void addCommandBefore(
            List<String> actionNames,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        actionNames.forEach(a -> addCommandBefore(a, command));
    }


    protected ServletService(HttpServlet servlet) {
        logger.info("started construction");
        this.servlet = servlet;
        logger.info("constructed");
    }

    /**
     * Determines child service to serve given request url and redirects request and response
     * to it.
     * @param request request to serve
     * @param response response to serve
     */
    @Override
    public final void accept(HttpServletRequest request, HttpServletResponse response) {
        commands.keySet().stream().filter(request.getRequestURI()::matches)
                .map(commands::get)
                .forEach(c -> c.accept(request, response));
    }
}
