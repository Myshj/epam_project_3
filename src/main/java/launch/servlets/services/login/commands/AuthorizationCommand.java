package launch.servlets.services.login.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;
import models.User;
import models.commands.FindUserByEmailAndPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.commands.CommandContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Command for user authorization.
 * Retrieves login and password fields from request parameters.
 */
public class AuthorizationCommand extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(AuthorizationCommand.class);
    private FindUserByEmailAndPassword command;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = command.withEmail(email).withPassword(password).execute().orElse(null);


        if (user != null) {
            logger.info("user authorized");
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userRole", user.getRole().getValue());
            response.sendRedirect("/admin/");
            return;
        }

        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        logger.info("executed");
    }

    public AuthorizationCommand(ServiceContext context) {
        super(context);
        logger.info("started construction");
        try {
            command = new FindUserByEmailAndPassword(
                    new CommandContext<>(
                            User.class,
                            context.getManagers().getRepository(),
                            context.getManagers().getConnection().get()
                    )
                    //ConnectionServiceProvider.INSTANCE.get()
            );
        } catch (SQLException e) {
            logger.error(e);

        }
        logger.info("constructed");
    }
}
