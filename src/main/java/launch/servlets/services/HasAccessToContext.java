package launch.servlets.services;

import launch.servlets.ServiceContext;

public abstract class HasAccessToContext {
    protected final ServiceContext context;

    public HasAccessToContext(ServiceContext context) {
        this.context = context;
    }
}
