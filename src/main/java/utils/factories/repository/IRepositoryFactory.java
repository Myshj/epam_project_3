package utils.factories.repository;

import orm.Model;
import orm.repository.IRepository;
import utils.managers.RepositoryManager;

/**
 * Base interface for all repository factories.
 */
public interface IRepositoryFactory {
    <T extends Model> IRepository<T> make(Class<T> clazz, RepositoryManager manager);
}
