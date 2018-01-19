package launch.servlets.services.admin.commands.generic;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.Repository;

/**
 * Base class for all commands that work with entities.
 *
 * @param <T> Type of entities to work with.
 */
public abstract class ModelCommand<T extends Model> extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ModelCommand.class);


    protected final Repository<T> repository;
    protected final Class<T> clazz;

    public ModelCommand(
            ServiceContext context,
            Class<T> clazz
    ) {
        super(context);
        logger.info("constructed");
        this.clazz = clazz;
        this.repository = context.getManagers().getRepository().get(clazz);
    }

}
