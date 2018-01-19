package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

/**
 * Base class for all querying commands.
 *
 * @param <T>
 */
public abstract class QueryCommand<T extends Model> extends DbCommand<T> {
    private static final Logger logger = LogManager.getLogger(QueryCommand.class);

    public QueryCommand(
            CommandContext<T> context,
            String sql
    ) {
        super(context, sql);
        logger.info("constructed");
    }
}
