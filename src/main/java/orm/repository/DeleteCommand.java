package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandContext;
import orm.commands.CommandWithNoReturn;

import java.sql.SQLException;

/**
 * Deletes entity from database.
 *
 * @param <T>
 */
final class DeleteCommand<T extends Model> extends CommandWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(DeleteCommand.class);

    public DeleteCommand(CommandContext<T> context) {
        super(
                context,
                String.format(
                        "DELETE FROM %s WHERE id=?;",
                        OrmFieldUtils.getTableName(context.getClazz())
                )
        );
        logger.info("created");
    }

    @Override
    public final void execute(T entity) {
        logger.info("started execution");
        try {
            statement.setLong(1, entity.getId().get().get());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("executed");
    }
}
