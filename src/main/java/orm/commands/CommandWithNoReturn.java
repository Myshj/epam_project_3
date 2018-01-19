package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

/**
 * Base class for all commands that do not return anything.
 *
 * @param <T>
 */
public abstract class CommandWithNoReturn<T extends Model> extends DbCommand<T> {
    private static final Logger logger = LogManager.getLogger(CommandWithNoReturn.class);


    public CommandWithNoReturn(CommandContext<T> context, String sql) {
        super(context, sql);
        logger.info("started construction");
        logger.info("constructed");
    }

    public abstract void execute(T entity);
}
