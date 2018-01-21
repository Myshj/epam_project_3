package utils.globals;

import utils.managers.resource.IResourceManager;

public interface IResourceManagers {
    IResourceManager getApplication();

    IResourceManager getUrls();
}
