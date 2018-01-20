package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;
import services.admin.commands.generic.includers.IncludeAll;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Displays list of all entities.
 *
 * @param <T> Type of entities to work with.
 */
public class ShowAllCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowAllCommand.class);

    private IncludeAll<T> includeAll;
    private ModelMetaInfo meta;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        includeAll.accept(request, response);
        request.setAttribute("meta", meta);
        dispatcher(
                url("adminListEntities")

        ).forward(request, response);
        logger.info("executed");
    }

    public ShowAllCommand(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context, clazz);
        logger.info("started construction");
        meta = MetaInfoManager.INSTANCE.get(clazz);
        includeAll = new IncludeAll<>(context, clazz, "entities");
        logger.info("construction");
    }
}
