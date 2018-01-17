package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandWithNoReturn;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Deletes entity from database.
 *
 * @param <T>
 */
final class DeleteCommand<T extends Model> extends CommandWithNoReturn<T> {
    private static final Logger logger = LogManager.getLogger(DeleteCommand.class);
    public DeleteCommand(Class<T> clazz, Connection connection) {
        super(
                clazz, connection,
                String.format(
                        "DELETE FROM %s WHERE id=?;",
                        OrmFieldUtils.getTableName(clazz)
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
