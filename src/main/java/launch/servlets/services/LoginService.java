package launch.servlets.services;

import launch.servlets.general.commands.AuthorizationCommand;
import launch.servlets.general.commands.SimpleForwarder;

import javax.servlet.http.HttpServlet;

public class LoginService extends ServletService {

    public LoginService(HttpServlet servlet) {
        super(servlet);

        registerCommand("/login", new SimpleForwarder(servlet).withUrl("/jsp/login.jsp"));
        registerCommand("/login/confirm", new AuthorizationCommand(servlet));
    }
}
