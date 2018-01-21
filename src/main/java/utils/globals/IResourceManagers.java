package utils.globals;

import utils.managers.resource.IResourceManager;

/**
 * Base interface for all classes containing resource managers.
 */
public interface IResourceManagers {
    IResourceManager getApplication();

    IResourceManager getUrls();
}
