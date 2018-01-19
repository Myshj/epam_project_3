package utils.globals;

import utils.managers.resource.IResourceManager;
import utils.managers.resource.ResourceBundleAccessor;
import utils.managers.resource.ResourceManager;

public class ResourceManagers {
    private final IResourceManager application = new ResourceManager(
            new ResourceBundleAccessor().withResource("application")
    );

    private final IResourceManager messages = new ResourceManager(
            new ResourceBundleAccessor().withResource("messages")
    );

    public IResourceManager getApplication() {
        return application;
    }

    public IResourceManager getMessages() {
        return messages;
    }
}
