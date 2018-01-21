package utils.globals;

import utils.managers.resource.IResourceManager;
import utils.managers.resource.ResourceBundleAccessor;
import utils.managers.resource.ResourceManager;

/**
 * Contains references to all resource managers.
 */
public class ResourceManagers implements IResourceManagers {
    private final IResourceManager application = new ResourceManager(
            new ResourceBundleAccessor().withResource("application")
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
