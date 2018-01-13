package launch.servlets.general;

import launch.servlets.services.MainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/admin/*", "/common/*", "/login/*"}
)
public class MyServlet extends HttpServlet {
    private final MainService mainService = new MainService(this);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainService.accept(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainService.accept(req, resp);
    }
}
