package launch.servlets.services;

import launch.servlets.services.admin.AdminService;
import launch.servlets.services.common.CommonService;
import launch.servlets.services.login.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

/**
 * The main service.
 * Serving /admin, /common and /login functionality.
 */
public class MainService extends ServletService {
    private static final Logger logger = LogManager.getLogger(MainService.class);

    public MainService(HttpServlet servlet) {
        super(servlet);
        logger.info("started construction");
        registerCommand("/admin.*", new AdminService(servlet));
        registerCommand("/common.*", new CommonService(servlet));
        registerCommand("/login.*", new LoginService(servlet));
        logger.info("constructed");
    }
}
