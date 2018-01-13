package launch.servlets.services.login.commands;

import launch.servlets.services.commands.ServletCommand;
import models.User;
import models.commands.FindUserByEmailAndPassword;
import utils.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationCommand extends ServletCommand {
    private FindUserByEmailAndPassword command;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public AuthorizationCommand(HttpServlet servlet) {
        super(servlet);
        try {
            command = new FindUserByEmailAndPassword(
                    User.class,
                    ConnectionManager.INSTANCE.get()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
