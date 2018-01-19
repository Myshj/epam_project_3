package launch.servlets.services;

import launch.servlets.ServiceContext;

public abstract class HasServiceContext {
    protected final ServiceContext context;

    public HasServiceContext(ServiceContext context) {
        this.context = context;
    }
}
