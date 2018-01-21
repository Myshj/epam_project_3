package services.login.commands;

import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logs user out.
 */
public class LogoutCommand extends ServletCommand {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("userRole", null);
        request.getSession().setAttribute("user", null);
        response.sendRedirect(url("mainPage"));
    }

    public LogoutCommand(ServletServiceContext context) {
        super(context);
    }
}
