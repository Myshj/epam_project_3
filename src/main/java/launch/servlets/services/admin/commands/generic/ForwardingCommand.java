package launch.servlets.services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletContext;

/**
 * Base class for all commands that eventually display list of entities.
 *
 * @param <T>
 */
public abstract class ForwardingCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ForwardingCommand.class);

    protected final ShowList<T> showList;

    public ForwardingCommand(
            ServletContext servlet,
            Repository<T> repository,
            ShowList<T> showList
    ) {
        super(servlet, repository);
        logger.info("constructed");
        this.showList = showList;
    }
}
