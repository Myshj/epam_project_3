package launch.servlets.services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.Repository;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Shows form for an existing entity editing.
 *
 * @param <T> Type of entities to work with.
 */
public class ShowUpdateFormCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowUpdateFormCommand.class);

    private ModelMetaInfo meta;

    public ShowUpdateFormCommand(
            Class<T> clazz,
            ServletContext servlet,
            Repository<T> repository
    ) {
        super(servlet, repository);
        logger.info("started construction");
        meta = MetaInfoManager.INSTANCE.get(clazz);
        logger.info("constructed");
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
            dispatcher(
                    "/jsp/admin/edit-entity.jsp"

            ).forward(request, response);
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.info("executed");
    }
}
