package launch.servlets.services.admin.commands.generic;

import launch.servlets.services.commands.ServletCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.Repository;

import javax.servlet.http.HttpServlet;

/**
 * Base class for all commands that work with entities.
 *
 * @param <T> Type of entities to work with.
 */
public abstract class ModelCommand<T extends Model> extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ModelCommand.class);


    protected Repository<T> repository;

    public ModelCommand(HttpServlet servlet, Repository<T> repository) {
        super(servlet);
        logger.info("constructed");
        this.repository = repository;
    }

}
