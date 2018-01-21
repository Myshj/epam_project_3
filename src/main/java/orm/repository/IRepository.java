package orm.repository;

import orm.Model;
import orm.repository.queries.ICountingQuery;
import orm.repository.queries.IGetEntityQuery;
import orm.repository.queries.IListEntitiesQuery;

import java.util.List;
import java.util.Optional;

/**
 * Base interface for all repositories.
 *
 * @param <T>
 */
public interface IRepository<T extends Model> {
    Optional<T> getById(long id);

    List<T> getAll();

    void delete(T entity);

    List<T> filter(IListEntitiesQuery<T> query);

    Optional<T> get(IGetEntityQuery<T> query);

    void save(T entity);

    Long count();

    Long count(ICountingQuery<T> query);
}
