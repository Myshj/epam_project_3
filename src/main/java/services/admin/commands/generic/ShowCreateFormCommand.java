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
import java.util.ArrayList;
import java.util.List;

/**
 * Shows form for new entity creation.
 *
 * @param <T> Type of entities to work with.
 */
public class ShowCreateFormCommand<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowCreateFormCommand.class);

    private ModelMetaInfo meta;

    private List<IncludeAll> includers = new ArrayList<>();

    public ShowCreateFormCommand(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context, clazz);
        logger.info("started construction");
        meta = MetaInfoManager.INSTANCE.get(clazz);
        logger.info("constructed");

        MetaInfoManager.INSTANCE.get(clazz).getRelatives().forEach(
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
