package launch.servlets.services;

import javax.servlet.http.HttpServlet;

public class MainService extends ServletService {
    public MainService(HttpServlet servlet) {
        super(servlet);
        registerCommand("/admin.*", new AdminService(servlet));
        registerCommand("/common.*", new CommonService(servlet));
        registerCommand("/login.*", new LoginService(servlet));
    }
}
