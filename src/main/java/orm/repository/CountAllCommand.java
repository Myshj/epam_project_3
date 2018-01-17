package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CountingCommand;

import java.sql.Connection;

/**
 * Counts ALL entities in a table.
 *
 * @param <T>
 */
public final class CountAllCommand<T extends Model> extends CountingCommand<T> {
    private static final Logger logger = LogManager.getLogger(CountAllCommand.class);
    public CountAllCommand(Class<T> clazz, Connection connection) {
        super(
                clazz, connection,
                String.format(
                        "SELECT COUNT(*) FROM %s;",
                        OrmFieldUtils.getTableName(clazz)
                )
        );
        logger.info("created");
    }
}
