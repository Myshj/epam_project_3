package orm.repository;

import orm.Model;
import orm.commands.CountingCommand;
import orm.commands.GetEntityCommand;
import orm.commands.ListEntitiesCommand;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Model> {
    Optional<T> getById(long id);

    List<T> getAll();

    void delete(T entity);

    List<T> filter(ListEntitiesCommand<T> command);

    Optional<T> get(GetEntityCommand<T> command);

    void save(T entity);

    Long count();

    Long count(CountingCommand<T> countingCommand);
}
