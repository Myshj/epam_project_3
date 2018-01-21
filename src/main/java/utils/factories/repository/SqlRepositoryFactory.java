package utils.factories.repository;

import orm.Model;
import orm.repository.impl.sql.SqlRepository;
import orm.repository.impl.sql.queries.SqlQueryContext;
import utils.managers.RepositoryManager;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Produces SQL-based repositories.
 */
public class SqlRepositoryFactory implements IRepositoryFactory {
    private final Supplier<Connection> connectionSupplier;

    public SqlRepositoryFactory(
            Supplier<Connection> connectionSupplier
    ) {
        this.connectionSupplier = connectionSupplier;
    }

    @Override
    public <T extends Model> SqlRepository<T> make(Class<T> clazz, RepositoryManager manager) {
        return new SqlRepository<>(new SqlQueryContext<>(clazz, manager, connectionSupplier.get()));
    }
}
