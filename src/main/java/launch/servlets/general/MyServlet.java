package launch.servlets.general;

import launch.servlets.general.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class MyServlet extends HttpServlet {
    protected final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<>();
    protected final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postActions = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("default", defaultGetAction());
        postActions.put("default", defaultPostAction());
    }

    protected final void addCommandBefore(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            String actionName,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        if (!actions.containsKey(actionName)) {
            return;
        }
        actions.put(actionName, command.andThen(actions.get(actionName)));
    }

    protected final void addCommandBefore(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            List<String> actionNames,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        actionNames.forEach(a -> addCommandBefore(actions, a, command));
    }

    private void callActionOrDefault(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            String action,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        (actions.containsKey(action) ? actions.get(action) : actions.get("default")).accept(request, response);
    }

    protected abstract ServletCommand defaultGetAction();

    protected abstract ServletCommand defaultPostAction();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        callActionOrDefault(getActions, req.getParameter("getAction"), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        callActionOrDefault(postActions, req.getParameter("postAction"), req, resp);
    }
}
