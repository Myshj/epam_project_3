package web.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.MainService;
import services.ServletServiceContext;
import utils.globals.Managers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Main servletContext controller.
 * Serves /admin, /common and /login urls.
 */
@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/settings/*", "/admin/*", "/common/*", "/login/*", "/registration/*", "/user/*"}
)
public class MyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MyServlet.class);

    private MainService mainService;

    private ServletServiceContext serviceContext;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceContext = new ServletServiceContext(
                getServletContext(),
                new Managers()
        );
        mainService = new MainService(
                serviceContext
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("started serving GET request");
        mainService.accept(req, resp);
        logger.info("served GET request");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("started serving POST request");
        mainService.accept(req, resp);
        logger.info("served POST request");
    }

    @Override
    public void destroy() {
        try {
            serviceContext.getManagers().getConnection().get().close();
        } catch (SQLException e) {
            logger.error("error while closing connection");
            logger.error(e);
        }
        super.destroy();
    }
}
