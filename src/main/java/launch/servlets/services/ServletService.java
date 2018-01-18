package launch.servlets.services;

import launch.servlets.services.commands.SimpleForwarder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Base class for all servletContext services.
 * Maps incoming requests to child services and commands.
 */
public abstract class ServletService implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    private static final Logger logger = LogManager.getLogger(ServletService.class);
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> commands = new HashMap<>();
    protected final ServletContext servletContext;

    private final SimpleForwarder notFoundRedirecter;
    private final SimpleForwarder errorRedirecter;

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
     *
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
     *
     * @param actionNames
     * @param command
     */
    protected final void addCommandBefore(
            List<String> actionNames,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        actionNames.forEach(a -> addCommandBefore(a, command));
    }


    protected ServletService(ServletContext servletContext) {
        logger.info("started construction");
        this.servletContext = servletContext;
        notFoundRedirecter = new SimpleForwarder(servletContext).withUrl("/jsp/error/errorPage.jsp");
        errorRedirecter = new SimpleForwarder(servletContext).withUrl("/jsp/error/errorPage.jsp");
        logger.info("constructed");
    }

    /**
     * Determines child service to serve given request url and redirects request and response
     * to it.
     *
     * @param request  request to serve
     * @param response response to serve
     */
    @Override
    public final void accept(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BiConsumer<HttpServletRequest, HttpServletResponse>> toExecute =
                    commands.keySet().stream()
                            .filter(request.getRequestURI()::matches)
                            .map(commands::get)
                            .collect(Collectors.toList());
            if (toExecute.isEmpty()) {
                notFoundRedirecter.accept(request, response);
            } else {
                toExecute.forEach(c -> c.accept(request, response));
            }
        } catch (Exception ex) {
            errorRedirecter.accept(request, response);
        }

    }
}
