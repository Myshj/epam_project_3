package utils.factories;

import orm.Model;
import orm.repository.IRepository;
import utils.managers.RepositoryManager;

public interface IRepositoryFactory {
    <T extends Model> IRepository<T> make(Class<T> clazz, RepositoryManager manager);
}
