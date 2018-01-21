package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;
import utils.converters.HttpServletRequestToEntityConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

/**
 * Updates entity in database using request parameters.
 *
 * @param <T> Type of entities to work with.
 */
public class UpdateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private static final Logger logger = LogManager.getLogger(ShowUpdateFormCommand.class);
    private Function<HttpServletRequest, T> updater;

    public UpdateEntityCommand(
            ServletServiceContext context,
            Class<T> clazz,
            ShowList<T> showList
    ) {
        super(context, clazz, showList);
        logger.info("started construction");
        updater = new HttpServletRequestToEntityConverter<>(context, clazz);
        logger.info("constructed");
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        T entity = updater.apply(request);
        entity.getId().setValue(Long.valueOf(request.getParameter("id")));
        repository.save(entity);

        request.setAttribute("id", entity.getId().getValue());
        response.sendRedirect(
                String.format(
                        url("adminShowAll"),
                        context.getManagers().getMetaInfo().apply(clazz).getNames().getSingular()
                )
        );

        //showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
