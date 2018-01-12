package launch.servlets.general;

import launch.servlets.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/general/*", "/admin/*"}
)
public class MyServlet extends HttpServlet implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    protected final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<>();

    final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> commands = new HashMap<>();

    //public Map<String, ModelAdminService> services = ModelServiceFactory.INSTANCE.create(this);

    @Override
    public void init() throws ServletException {
        super.init();

        commands.put("/admin/", new AdminService(this));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accept(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accept(req, resp);
    }

    @Override
    public void accept(HttpServletRequest request, HttpServletResponse response) {
        commands.keySet().stream().filter(s -> request.getRequestURI().startsWith(s))
                .map(commands::get)
                .forEach(c -> c.accept(request, response));
    }
}
