package orm.repository;

import orm.Model;
import orm.queries.CountingQuery;
import orm.queries.GetEntityQuery;
import orm.queries.ListEntitiesQuery;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Model> {
    Optional<T> getById(long id);

    List<T> getAll();

    void delete(T entity);

    List<T> filter(ListEntitiesQuery<T> query);

    Optional<T> get(GetEntityQuery<T> query);

    void save(T entity);

    Long count();

    Long count(CountingQuery<T> query);
}
