package launch.servlets.services;

import launch.servlets.ServiceContext;
import launch.servlets.services.admin.AdminService;
import launch.servlets.services.common.CommonService;
import launch.servlets.services.login.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main service.
 * Serving /admin, /common and /login functionality.
 */
public class MainService extends ServletService {
    private static final Logger logger = LogManager.getLogger(MainService.class);

    public MainService(ServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand("/admin.*", new AdminService(context));
        registerCommand("/common.*", new CommonService(context));
        registerCommand("/login.*", new LoginService(context));
        logger.info("constructed");
    }
}
