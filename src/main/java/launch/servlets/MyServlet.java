package launch.servlets;

import launch.servlets.services.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet controller.
 * Serves /admin, /common and /login urls.
 */
@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/admin/*", "/common/*", "/login/*"}
)
public class MyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MyServlet.class);

    private final MainService mainService = new MainService(this);

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
}
