package launch.servlets.services;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.SimpleForwarder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public abstract class ServletService extends HasAccessToContext implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    private static final Logger logger = LogManager.getLogger(ServletService.class);
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> commands = new HashMap<>();

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


    protected ServletService(ServiceContext context) {
        super(context);
        logger.info("started construction");
        notFoundRedirecter = new SimpleForwarder(context).withUrl("/jsp/error/errorPage.jsp");
        errorRedirecter = new SimpleForwarder(context).withUrl("/jsp/error/errorPage.jsp");
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
