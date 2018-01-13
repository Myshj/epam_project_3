package launch.servlets.services.login;

import launch.servlets.services.ServletService;
import launch.servlets.services.commands.SimpleForwarder;
import launch.servlets.services.login.commands.AuthorizationCommand;

import javax.servlet.http.HttpServlet;

public class LoginService extends ServletService {

    public LoginService(HttpServlet servlet) {
        super(servlet);

        registerCommand("/login", new SimpleForwarder(servlet).withUrl("/jsp/login.jsp"));
        registerCommand("/login/confirm", new AuthorizationCommand(servlet));
    }
}
