package services.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletService;
import services.ServletServiceContext;
import services.commands.SimpleForwarder;
import services.login.commands.AuthorizationCommand;

/**
 * Service for user authorization and authentication.
 */
public class LoginService extends ServletService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);

    public LoginService(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand(url("login"), new SimpleForwarder(context).withUrl(url("loginJsp")));
        registerCommand(url("loginConfirm"), new AuthorizationCommand(context));
        logger.info("constructed");
    }
}
