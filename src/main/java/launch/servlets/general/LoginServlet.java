package launch.servlets.general;

import models.User;
import models.commands.FindUserByEmailAndPassword;
import orm.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {
    private FindUserByEmailAndPassword command;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            command = new FindUserByEmailAndPassword(
                    User.class,
                    ConnectionManager.INSTANCE.get()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = command.withEmail(email).withPassword(password).execute().orElse(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user != null) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userRole", user.getRole().getValue());
            response.sendRedirect("/admin/city");
            return;
        }

        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
