package utils.managers;

import orm.Model;
import orm.repository.Repository;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NewRepositoryManager {
    private Map<Class, Repository> repositories = new HashMap<>();
    private Supplier<Connection> connectionSupplier;

    public NewRepositoryManager(Supplier<Connection> connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    /**
     * Returns repository for a given model class.
     *
     * @param clazz class of model.
     * @param <T>   class of model
     * @return repository for a given model class or null if any exception occured
     */
    public <T extends Model> Repository<T> get(Class<T> clazz) {
        try {
            Repository<T> r = new Repository<>(
                    clazz,
                    connectionSupplier.get()
                    //ConnectionServiceProvider.INSTANCE.get()
            );
            repositories.putIfAbsent(clazz, r);
            return repositories.get(clazz);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}
