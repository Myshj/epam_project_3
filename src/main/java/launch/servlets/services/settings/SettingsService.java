package launch.servlets.services.settings;

import launch.servlets.ServiceContext;
import launch.servlets.services.ServletService;
import launch.servlets.services.settings.commands.ChangeSettingsCommand;
import launch.servlets.services.settings.commands.ShowSettingsCommand;

public class SettingsService extends ServletService {
    public SettingsService(ServiceContext context) {
        super(context);
        registerCommand("/settings/confirm(/)*", new ChangeSettingsCommand(context));
        registerCommand("/settings(/)*", new ShowSettingsCommand(context));
    }
}
