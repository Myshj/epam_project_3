package services;

public abstract class HasServiceContext {
    protected final ServletServiceContext context;

    protected String url(String key) {
        try {
            return context.getManagers().getResources().getUrls().withKey(key).get();
        } catch (Exception ex) {
            return key;
        }
    }

    public HasServiceContext(ServletServiceContext context) {
        this.context = context;
    }
}
