package launch.servlets.services.admin.commands.generic;

import launch.servlets.ServiceContext;
import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
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
                "/jsp/admin/list-entities.jsp"

        ).forward(request, response);
        logger.info("executed");
    }

    public ShowAllCommand(
            ServiceContext context,
            Class<T> clazz
    ) {
        super(context, clazz);
        logger.info("started construction");
        meta = MetaInfoManager.INSTANCE.get(clazz);
        includeAll = new IncludeAll<>(context, clazz, "entities");
        logger.info("construction");
    }
}
