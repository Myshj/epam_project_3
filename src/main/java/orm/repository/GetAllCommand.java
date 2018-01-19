package orm.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.OrmFieldUtils;
import orm.commands.CommandContext;
import orm.commands.ListEntitiesCommand;

/**
 * Returns ALL stored entities of such class.
 *
 * @param <T>
 */
final class GetAllCommand<T extends Model> extends ListEntitiesCommand<T> {
    private static final Logger logger = LogManager.getLogger(GetAllCommand.class);

    GetAllCommand(CommandContext<T> context) {
        super(
                context,
                String.format(
                        "SELECT * FROM %s;",
                        OrmFieldUtils.getTableName(context.getClazz())
                )
        );
        logger.info("created");
    }
}
