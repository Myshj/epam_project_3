package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandContext;
import orm.commands.CountingCommand;

/**
 * Counts ALL entities in a table.
 *
 * @param <T>
 */
public final class CountAllCommand<T extends Model> extends CountingCommand<T> {
    private static final Logger logger = LogManager.getLogger(CountAllCommand.class);

    public CountAllCommand(CommandContext<T> context) {
        super(
                context,
                String.format(
                        "SELECT COUNT(*) FROM %s;",
                        OrmFieldUtils.getTableName(context.getClazz())
                )
        );
        logger.info("created");
    }
}
