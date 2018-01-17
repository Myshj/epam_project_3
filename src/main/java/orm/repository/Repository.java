package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.commands.CountingCommand;
import orm.commands.GetEntityCommand;
import orm.commands.ListEntitiesCommand;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Repository for entities.
 *
 * @param <T> Type of served entities.
 */
public class Repository<T extends Model> {
    private static final Logger logger = LogManager.getLogger(Repository.class);

    private InsertCommand<T> insertCommand;
    private UpdateCommand<T> updateCommand;
    private DeleteCommand<T> deleteCommand;
    private GetByIdCommand<T> getByIdCommand;
    private GetAllCommand<T> getAllCommand;
    private CountAllCommand<T> countAllCommand;

    /**
     * Constructor.
     *
     * @param clazz      Class of served entities.
     * @param connection Connection to work with.
     */
    public Repository(Class<T> clazz, Connection connection) {
        logger.info("started construction");
        insertCommand = new InsertCommand<>(clazz, connection);
        updateCommand = new UpdateCommand<>(clazz, connection);
        deleteCommand = new DeleteCommand<>(clazz, connection);
        getByIdCommand = new GetByIdCommand<>(clazz, connection);
        getAllCommand = new GetAllCommand<>(clazz, connection);
        countAllCommand = new CountAllCommand<>(clazz, connection);
        logger.info("constructed");
    }

    /**
     * Shortcut for calling get(GetByIdCommand).
     * @param id id of entity to search for.
     * @return Optional describing returned entity.
     */
    public Optional<T> getById(long id) {
        return get(getByIdCommand.withId(id));
    }

    /**
     * Shortcut for calling filter(GetAllCommand).
     * @return list of all entities in a table.
     */
    public List<T> getAll() {
        return filter(getAllCommand);
    }

    /**
     * Deletes entity from database.
     * @param entity entity to delete.
     */
    public void delete(T entity) {
        deleteCommand.execute(entity);
    }

    /**
     * Queries database for a list of entities.
     *
     * @param command command to execute.
     * @return list of entities returned by command.
     */
    public List<T> filter(ListEntitiesCommand<T> command) {
        return command.execute();
    }

    /**
     * Queries database for a single entity.
     *
     * @param command command to execute
     * @return Optional describing entity returned by command
     */
    public Optional<T> get(GetEntityCommand<T> command) {
        return command.execute();
    }

    /**
     * If given entity exists in database then updates it.
     * Inserts new entity otherwise.
     *
     * @param entity entity to insert or update.
     */
    public void save(T entity) {
        logger.info("started saving");
        if (entity.getId().get().isPresent()) {
            logger.info("entity has id --> updating");
            updateCommand.execute(entity);
        } else {
            logger.info("entity does not have id --> inserting");
            insertCommand.execute(entity);
        }
        logger.info("saved");
    }

    /**
     *
     * @return count of all entities in table.
     */
    public Long count() {
        return count(countAllCommand);
    }

    /**
     * Queries database for a count of entities.
     *
     * @param countingCommand command to execute.
     * @return count of entities.
     */
    public Long count(CountingCommand<T> countingCommand) {
        return countingCommand.execute();
    }
}
