package utils.globals;

import utils.managers.ConnectionManager;
import utils.managers.NewRepositoryManager;

public class Managers {
    private final ResourceManagers resources = new ResourceManagers();

    private final ConnectionManager connection = new ConnectionManager(resources.getApplication());
    private final NewRepositoryManager repository = new NewRepositoryManager(connection);

    public ConnectionManager getConnection() {
        return connection;
    }

    public NewRepositoryManager getRepository() {
        return repository;
    }

    public ResourceManagers getResources() {
        return resources;
    }
}
