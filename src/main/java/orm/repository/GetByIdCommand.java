package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.GetEntityCommand;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Selects entity by id.
 *
 * @param <T>
 */
final class GetByIdCommand<T extends Model> extends GetEntityCommand<T> {
    private static final Logger logger = LogManager.getLogger(GetByIdCommand.class);
    GetByIdCommand(
            Class<T> clazz,
            Connection connection
    )  {
        super(
                clazz, connection,
                String.format(
                        "SELECT * from %s WHERE id=?;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
        logger.info("created");
    }

    GetByIdCommand<T> withId(long id) {
        logger.info("started remembering id");
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("remembered id");
        return this;
    }
}
