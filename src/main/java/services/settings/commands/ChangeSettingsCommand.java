package services.settings.commands;

import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Changes current user settings.
 */
public class ChangeSettingsCommand extends ServletCommand {
    @Override
    protected void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getSession().setAttribute("language", request.getParameter("language"));
        response.sendRedirect(url("settings"));
        //dispatcher(request.getParameter("from")).forward(request, response);
    }

    public ChangeSettingsCommand(ServletServiceContext context) {
        super(context);
    }
}
