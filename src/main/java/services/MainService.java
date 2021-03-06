package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.admin.AdminService;
import services.common.CommonService;
import services.login.LoginService;
import services.registration.RegistrationService;
import services.settings.SettingsService;
import services.user.UserService;

/**
 * The main service.
 * Serving /admin, /common and  functionality.
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
        registerCommand(url("mainRegistration"), new RegistrationService(context));
        registerCommand(url("mainUser"), new UserService(context));
        logger.info("constructed");
    }
}
