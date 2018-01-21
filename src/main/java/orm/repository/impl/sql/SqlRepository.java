package orm.repository.impl.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.IRepository;
import orm.repository.impl.sql.queries.CountingQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;
import orm.repository.queries.ICountingQuery;
import orm.repository.queries.IGetEntityQuery;
import orm.repository.queries.IListEntitiesQuery;

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

    private InsertQuery<T> insertQuery;
    private UpdateQuery<T> updateQuery;
    private DeleteQuery<T> deleteQuery;
    private GetByIdQuery<T> getByIdQuery;
    private GetAllQuery<T> getAllQuery;
    private CountingQuery<T> countAllQuery;

    /**
     * Constructor.
     *
     */
    public SqlRepository(SqlQueryContext<T> queryContext) {
        logger.info("started construction");
        insertQuery = new InsertQuery<>(queryContext);
        updateQuery = new UpdateQuery<>(queryContext);
        deleteQuery = new DeleteQuery<>(queryContext);
        getByIdQuery = new GetByIdQuery<>(queryContext);
        getAllQuery = new GetAllQuery<>(queryContext);
        countAllQuery = new CountAllQuery<>(queryContext);
        logger.info("constructed");
    }

    /**
     * Shortcut for calling get(GetByIdQuery).
     * @param id id of entity to search for.
     * @return Optional describing returned entity.
     */
    @Override
    public Optional<T> getById(long id) {
        return get(getByIdQuery.withId(id));
    }

    /**
     * Shortcut for calling filter(GetAllQuery).
     * @return list of all entities in a table.
     */
    @Override
    public List<T> getAll() {
        return filter(getAllQuery);
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
        deleteQuery.accept(entity);
    }

    /**
     * Queries database for a list of entities.
     *
     * @param query command to execute.
     * @return list of entities returned by command.
     */
    @Override
    public List<T> filter(IListEntitiesQuery<T> query) {
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
    public Optional<T> get(IGetEntityQuery<T> query) {
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
            updateQuery.accept(entity);
        } else {
            logger.info("entity does not have id --> inserting");
            insertQuery.accept(entity);
        }
        logger.info("saved");
    }

    /**
     *
     * @return count of all entities in table.
     */
    @Override
    public Long count() {
        return count(countAllQuery);
    }

    /**
     * Queries database for a count of entities.
     *
     * @param query command to execute.
     * @return count of entities.
     */
    @Override
    public Long count(ICountingQuery<T> query) {
        if (null == query) {
            logger.error("nothing to execute --> return null");
            return null;
        }
        return query.get();
    }
}
