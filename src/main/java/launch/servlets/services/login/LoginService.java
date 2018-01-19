package launch.servlets.services.login;

import launch.servlets.ServiceContext;
import launch.servlets.services.ServletService;
import launch.servlets.services.commands.SimpleForwarder;
import launch.servlets.services.login.commands.AuthorizationCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service for user authorization and authentication.
 */
public class LoginService extends ServletService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);

    public LoginService(ServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand("/login", new SimpleForwarder(context).withUrl("/jsp/login.jsp"));
        registerCommand("/login/confirm", new AuthorizationCommand(context));
        logger.info("constructed");
    }
}
