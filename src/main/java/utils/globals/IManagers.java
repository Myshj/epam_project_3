package utils.globals;

import utils.managers.ConnectionManager;
import utils.managers.MetaInfoManager;
import utils.managers.RepositoryManager;

public interface IManagers {
    ConnectionManager getConnection();

    RepositoryManager getRepository();

    IResourceManagers getResources();

    MetaInfoManager getMetaInfo();
}
