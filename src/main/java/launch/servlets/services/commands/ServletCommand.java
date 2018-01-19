package launch.servlets.services.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.HasAccessToContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * Base class for all end commands executed by servlets and their services.
 */
public abstract class ServletCommand extends HasAccessToContext implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    private static final Logger logger = LogManager.getLogger(ServletCommand.class);

    public ServletCommand(ServiceContext context) {
        super(context);
    }

    /**
     * Get RequestDispatcher from related ServletContext.
     *
     * @param url URL to dispatch
     * @return dispatcher serving url.
     */
    protected final RequestDispatcher dispatcher(String url) {
        return context.getServlet().getRequestDispatcher(url);
    }

    /**
     * Executes command and supresses possible exceptions.
     * @param request request to serve
     * @param response response to serve
     */
    @Override
    public final void accept(HttpServletRequest request, HttpServletResponse response) {
        logger.info("started serving");
        try {
            execute(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("served");
    }

    /**
     * Main execution method.
     * @param request request to serve
     * @param response response to serve
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
