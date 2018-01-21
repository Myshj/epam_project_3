package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;
import services.commands.includers.IncludeListToRequest;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Displays list of entities in a table.
 *
 * @param <T> Type of entities to work with.
 */
public class ShowList<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowList.class);

    private IncludeListToRequest<T> includer;
    private ModelMetaInfo meta;

    public ShowList(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context, clazz);
        logger.info("started construction");
        this.includer = new IncludeListToRequest<>(context, clazz, "entities");
        meta = meta(clazz);
        logger.info("constructed");
    }

    public ShowList withList(List<T> list) {
        logger.info("received list to remember");
        includer.withList(list);
        logger.info("remembered list");
        return this;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        logger.info("started execution");
        includer.execute(request, response);
        request.setAttribute("meta", meta);
        dispatcher(
                url("adminListEntities")

        ).forward(request, response);
        logger.info("executed");
    }
}
