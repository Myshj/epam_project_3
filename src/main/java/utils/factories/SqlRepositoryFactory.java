package utils.factories;

import orm.Model;
import orm.commands.CommandContext;
import orm.repository.SqlRepository;
import utils.managers.RepositoryManager;

import java.sql.Connection;
import java.util.function.Supplier;

public class SqlRepositoryFactory implements IRepositoryFactory {
    private final Supplier<Connection> connectionSupplier;

    public SqlRepositoryFactory(
            Supplier<Connection> connectionSupplier
    ) {
        this.connectionSupplier = connectionSupplier;
    }

    @Override
    public <T extends Model> SqlRepository<T> make(Class<T> clazz, RepositoryManager manager) {
        return new SqlRepository<>(new CommandContext<>(clazz, manager, connectionSupplier.get()));
    }
}
