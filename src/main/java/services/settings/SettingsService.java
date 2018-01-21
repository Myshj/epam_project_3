package services.settings;

import services.ServletService;
import services.ServletServiceContext;
import services.settings.commands.ChangeSettingsCommand;
import services.settings.commands.ShowSettingsCommand;

/**
 * Manages user settings.
 */
public class SettingsService extends ServletService {
    public SettingsService(ServletServiceContext context) {
        super(context);
        registerCommand(url("settingsConfirmPattern"), new ChangeSettingsCommand(context));
        registerCommand(url("settingsPattern"), new ShowSettingsCommand(context));
    }
}
