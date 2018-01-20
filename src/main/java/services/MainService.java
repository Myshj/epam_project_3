package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.admin.AdminService;
import services.common.CommonService;
import services.login.LoginService;
import services.settings.SettingsService;

/**
 * The main service.
 * Serving /admin, /common and /login functionality.
 */
public class MainService extends ServletService {
    private static final Logger logger = LogManager.getLogger(MainService.class);

    public MainService(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand(url("mainAdmin"), new AdminService(context));
        registerCommand(url("mainCommon"), new CommonService(context));
        registerCommand(url("mainLogin"), new LoginService(context));
        registerCommand(url("mainSettings"), new SettingsService(context));
        logger.info("constructed");
    }
}
