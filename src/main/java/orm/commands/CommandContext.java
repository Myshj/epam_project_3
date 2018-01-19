package orm.commands;

import orm.Model;
import utils.managers.RepositoryManager;

import java.sql.Connection;

public class CommandContext<T extends Model> {
    private final Class<T> clazz;
    private final RepositoryManager repositoryManager;
    private final Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public RepositoryManager getRepositoryManager() {

        return repositoryManager;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public CommandContext(
            Class<T> clazz,
            RepositoryManager repositoryManager,
            Connection connection
    ) {
        this.clazz = clazz;
        this.repositoryManager = repositoryManager;
        this.connection = connection;
    }
}
