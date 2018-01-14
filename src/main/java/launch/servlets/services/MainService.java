package launch.servlets.services;

import launch.servlets.services.admin.AdminService;
import launch.servlets.services.common.CommonService;
import launch.servlets.services.login.LoginService;

import javax.servlet.http.HttpServlet;

public class MainService extends ServletService {
    public MainService(HttpServlet servlet) {
        super(servlet);
        registerCommand("/admin.*", new AdminService(servlet));
        registerCommand("/common.*", new CommonService(servlet));
        registerCommand("/login.*", new LoginService(servlet));
    }
}
