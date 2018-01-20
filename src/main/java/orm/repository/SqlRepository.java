package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.queries.CountingQuery;
import orm.queries.GetEntityQuery;
import orm.queries.ListEntitiesQuery;
import orm.queries.SqlQueryContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SqlRepository for entities.
 *
 * @param <T> Type of served entities.
 */
public class SqlRepository<T extends Model> implements IRepository<T> {
    private static final Logger logger = LogManager.getLogger(SqlRepository.class);

    private InsertQuery<T> insertCommand;
    private UpdateQuery<T> updateCommand;
    private DeleteQuery<T> deleteCommand;
    private GetByIdQuery<T> getByIdCommand;
    private GetAllQuery<T> getAllCommand;
    private CountAllQuery<T> countAllCommand;

    /**
     * Constructor.
     *
     */
    public SqlRepository(SqlQueryContext<T> queryContext) {
        logger.info("started construction");
        insertCommand = new InsertQuery<>(queryContext);
        updateCommand = new UpdateQuery<>(queryContext);
        deleteCommand = new DeleteQuery<>(queryContext);
        getByIdCommand = new GetByIdQuery<>(queryContext);
        getAllCommand = new GetAllQuery<>(queryContext);
        countAllCommand = new CountAllQuery<>(queryContext);
        logger.info("constructed");
    }

    /**
     * Shortcut for calling get(GetByIdQuery).
     * @param id id of entity to search for.
     * @return Optional describing returned entity.
     */
    @Override
    public Optional<T> getById(long id) {
        return get(getByIdCommand.withId(id));
    }

    /**
     * Shortcut for calling filter(GetAllQuery).
     * @return list of all entities in a table.
     */
    @Override
    public List<T> getAll() {
        return filter(getAllCommand);
    }

    /**
     * Deletes entity from database.
     * @param entity entity to delete.
     */
    @Override
    public void delete(T entity) {
        if (null == entity) {
            logger.error("nothing to delete --> doing nothing");
            return;
        }
        deleteCommand.accept(entity);
    }

    /**
     * Queries database for a list of entities.
     *
     * @param query command to execute.
     * @return list of entities returned by command.
     */
    @Override
    public List<T> filter(ListEntitiesQuery<T> query) {
        if (null == query) {
            logger.error("nothing to execute --> return empty list");
            return new ArrayList<>();
        }
        return query.get();
    }

    /**
     * Queries database for a single entity.
     *
     * @param query command to execute
     * @return Optional describing entity returned by command
     */
    @Override
    public Optional<T> get(GetEntityQuery<T> query) {
        if (null == query) {
            logger.error("nothing to execute --> return null");
            return Optional.empty();
        }
        return Optional.ofNullable(query.get());
    }

    /**
     * If given entity exists in database then updates it.
     * Inserts new entity otherwise.
     *
     * @param entity entity to insert or update.
     */
    @Override
    public void save(T entity) {
        logger.info("started saving");
        if (null == entity) {
            logger.error("nothing to save --> doing nothing");
            return;
        }
        if (entity.getId().get().isPresent()) {
            logger.info("entity has id --> updating");
            updateCommand.accept(entity);
        } else {
            logger.info("entity does not have id --> inserting");
            insertCommand.accept(entity);
        }
        logger.info("saved");
    }

    /**
     *
     * @return count of all entities in table.
     */
    @Override
    public Long count() {
        return count(countAllCommand);
    }

    /**
     * Queries database for a count of entities.
     *
     * @param query command to execute.
     * @return count of entities.
     */
    @Override
    public Long count(CountingQuery<T> query) {
        if (null == query) {
            logger.error("nothing to execute --> return null");
            return null;
        }
        return query.get();
    }
}
