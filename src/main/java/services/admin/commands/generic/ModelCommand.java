package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.IRepository;
import services.ServletServiceContext;
import services.commands.ServletCommand;

/**
 * Base class for all queries that work with entities.
 *
 * @param <T> Type of entities to work with.
 */
public abstract class ModelCommand<T extends Model> extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ModelCommand.class);


    protected final IRepository<T> repository;
    protected final Class<T> clazz;

    public ModelCommand(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context);
        logger.info("constructed");
        this.clazz = clazz;
        this.repository = context.getManagers().getRepository().get(clazz);
    }

}
