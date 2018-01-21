package utils.managers;

import orm.Model;
import orm.repository.IRepository;
import utils.factories.repository.IRepositoryFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages repositories.
 */
public class RepositoryManager {
    private final Map<Class, IRepository> repositories = new HashMap<>();
    private final IRepositoryFactory repositoryFactory;

    public RepositoryManager(IRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    /**
     * Returns repository for a given model class.
     *
     * @param <T>   class of model
     * @param clazz class of model.
     * @return repository for a given model class or null if any exception occured
     */
    public <T extends Model> IRepository<T> get(Class<T> clazz) {
        repositories.putIfAbsent(clazz, repositoryFactory.make(clazz, this));
        return repositories.get(clazz);
    }
}
