package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;
import services.commands.includers.IncludeAll;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows form for an existing entity editing.
 *
 * @param <T> Type of entities to work with.
 */
public class ShowUpdateFormCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowUpdateFormCommand.class);

    private ModelMetaInfo meta;
    private List<IncludeAll> includers = new ArrayList<>();

    public ShowUpdateFormCommand(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context, clazz);
        logger.info("started construction");
        meta = meta(clazz);
        logger.info("constructed");

        meta(clazz).getRelatives().forEach(
                (k, v) -> includers.add(
                        new IncludeAll<>(context, v, k)
                )
        );
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        logger.info("started execution");
        try {
            T entity = repository.getById(
                    Long.valueOf(request.getParameter("id"))
            ).orElse(null);
            request.setAttribute(
                    "entity",
                    entity
            );
            request.setAttribute("action", "update");
            request.setAttribute("meta", meta);
            includers.forEach(i -> i.accept(request, response));
            dispatcher(
                    url("adminEditEntity")

            ).forward(request, response);
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.info("executed");
    }
}
