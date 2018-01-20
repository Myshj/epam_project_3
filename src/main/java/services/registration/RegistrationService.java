package services.registration;

import services.ServletService;
import services.ServletServiceContext;
import services.commands.SimpleForwarder;
import services.registration.commands.RegisterUser;

public class RegistrationService extends ServletService {
    public RegistrationService(ServletServiceContext context) {
        super(context);
        registerCommand(url("showRegistrationFormPattern"), new SimpleForwarder(context).withUrl(url("registrationFormJsp")));
        registerCommand(url("registerPattern"), new RegisterUser(context));
    }
}
