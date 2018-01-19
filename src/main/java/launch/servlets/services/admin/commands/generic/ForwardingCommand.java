package launch.servlets.services.admin.commands.generic;

import launch.servlets.ServiceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

/**
 * Base class for all commands that eventually display list of entities.
 *
 * @param <T>
 */
public abstract class ForwardingCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ForwardingCommand.class);

    protected final ShowList<T> showList;

    public ForwardingCommand(
            ServiceContext context,
            Class<T> clazz,
            ShowList<T> showList
    ) {
        super(context, clazz);
        logger.info("constructed");
        this.showList = showList;
    }
}
