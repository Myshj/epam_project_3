package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.commands.CommandContext;
import orm.commands.CountingCommand;
import orm.commands.GetEntityCommand;
import orm.commands.ListEntitiesCommand;

import java.util.List;
import java.util.Optional;

/**
 * SqlRepository for entities.
 *
 * @param <T> Type of served entities.
 */
public class SqlRepository<T extends Model> implements IRepository<T> {
    private static final Logger logger = LogManager.getLogger(SqlRepository.class);

    private InsertCommand<T> insertCommand;
    private UpdateCommand<T> updateCommand;
    private DeleteCommand<T> deleteCommand;
    private GetByIdCommand<T> getByIdCommand;
    private GetAllCommand<T> getAllCommand;
    private CountAllCommand<T> countAllCommand;

    /**
     * Constructor.
     *
     */
    public SqlRepository(CommandContext<T> commandContext) {
        logger.info("started construction");
        insertCommand = new InsertCommand<>(commandContext);
        updateCommand = new UpdateCommand<>(commandContext);
        deleteCommand = new DeleteCommand<>(commandContext);
        getByIdCommand = new GetByIdCommand<>(commandContext);
        getAllCommand = new GetAllCommand<>(commandContext);
        countAllCommand = new CountAllCommand<>(commandContext);
        logger.info("constructed");
    }

    /**
     * Shortcut for calling get(GetByIdCommand).
     * @param id id of entity to search for.
     * @return Optional describing returned entity.
     */
    @Override
    public Optional<T> getById(long id) {
        return get(getByIdCommand.withId(id));
    }

    /**
     * Shortcut for calling filter(GetAllCommand).
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
        deleteCommand.execute(entity);
    }

    /**
     * Queries database for a list of entities.
     *
     * @param command command to execute.
     * @return list of entities returned by command.
     */
    @Override
    public List<T> filter(ListEntitiesCommand<T> command) {
        return command.execute();
    }

    /**
     * Queries database for a single entity.
     *
     * @param command command to execute
     * @return Optional describing entity returned by command
     */
    @Override
    public Optional<T> get(GetEntityCommand<T> command) {
        return command.execute();
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
    @Override
    public Long count() {
        return count(countAllCommand);
    }

    /**
     * Queries database for a count of entities.
     *
     * @param countingCommand command to execute.
     * @return count of entities.
     */
    @Override
    public Long count(CountingCommand<T> countingCommand) {
        return countingCommand.execute();
    }
}
