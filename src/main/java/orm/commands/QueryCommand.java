package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import java.sql.Connection;

/**
 * Base class for all querying commands.
 *
 * @param <T>
 */
public abstract class QueryCommand<T extends Model> extends DbCommand<T> {
    private static final Logger logger = LogManager.getLogger(QueryCommand.class);

    public QueryCommand(
            Class<T> clazz,
            Connection connection,
            String sql
    ) {
        super(clazz, connection, sql);
        logger.info("constructed");
    }
}
