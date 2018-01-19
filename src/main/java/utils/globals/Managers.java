package utils.globals;

import utils.factories.SqlRepositoryFactory;
import utils.managers.ConnectionManager;
import utils.managers.RepositoryManager;

public class Managers implements IManagers {
    private final ResourceManagers resources = new ResourceManagers();

    private final ConnectionManager connection = new ConnectionManager(resources.getApplication());

    private final RepositoryManager repository = new RepositoryManager(new SqlRepositoryFactory(connection));

    @Override
    public ConnectionManager getConnection() {
        return connection;
    }

    @Override
    public RepositoryManager getRepository() {
        return repository;
    }

    @Override
    public ResourceManagers getResources() {
        return resources;
    }
}
