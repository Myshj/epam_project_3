package utils.globals;

import utils.managers.resource.IResourceManager;
import utils.managers.resource.ResourceBundleAccessor;
import utils.managers.resource.ResourceManager;

public class TestResourceManagers implements IResourceManagers {
    private final IResourceManager application = new ResourceManager(
            new ResourceBundleAccessor().withResource("application_test")
    );

    private final IResourceManager urls = new ResourceManager(
            new ResourceBundleAccessor().withResource("urls")
    );

    @Override
    public IResourceManager getApplication() {
        return application;
    }

    @Override
    public IResourceManager getUrls() {
        return urls;
    }
}