package launch.servlets.services.settings.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeSettingsCommand extends ServletCommand {
    @Override
    protected void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getSession().setAttribute("language", request.getParameter("language"));
        response.sendRedirect("/settings");
        //dispatcher(request.getParameter("from")).forward(request, response);
    }

    public ChangeSettingsCommand(ServiceContext context) {
        super(context);
    }
}
