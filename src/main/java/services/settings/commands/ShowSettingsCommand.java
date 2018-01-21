package services.settings.commands;

import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shows settings page.
 */
public class ShowSettingsCommand extends ServletCommand {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher(url("settingsJsp")).forward(request, response);
    }

    public ShowSettingsCommand(ServletServiceContext context) {
        super(context);
    }
}
