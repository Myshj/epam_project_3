package orm.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

/**
 * Base class for all commands that do not return anything.
 *
 * @param <T>
 */
public abstract class CommandWithNoReturn<T extends Model> extends DbCommand<T> {
    private static final Logger logger = LogManager.getLogger(CommandWithNoReturn.class);

    protected Map<Field, Integer> queryParametersMap;

    public CommandWithNoReturn(Class<T> clazz, Connection connection, String sql) {
        super(clazz, connection, sql);
        logger.info("started construction");
        queryParametersMap = OrmFieldUtils.getUpdateMapping(clazz);
        logger.info("constructed");
    }

    public abstract void execute(T entity);
}
