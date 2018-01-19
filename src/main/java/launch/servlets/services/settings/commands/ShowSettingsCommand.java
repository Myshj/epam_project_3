package launch.servlets.services.settings.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowSettingsCommand extends ServletCommand {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher("/jsp/general/settings.jsp").forward(request, response);
    }

    public ShowSettingsCommand(ServiceContext context) {
        super(context);
    }
}
