package services.login.commands;

import models.User;
import models.queries.FindUserByEmailAndPassword;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.SqlQueryContext;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        User user = command.withEmail(email).withPassword(password).get();


        if (user != null) {
            logger.info("user authorized");
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userRole", user.getRole().getValue());
            response.sendRedirect(url("mainPage"));
            return;
        }

        request.getRequestDispatcher(url("loginJsp")).forward(request, response);
        logger.info("executed");
    }

    public AuthorizationCommand(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        command = new FindUserByEmailAndPassword(
                new SqlQueryContext<>(
                        User.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
                //ConnectionServiceProvider.INSTANCE.get()
        );
        logger.info("constructed");
    }
}
