package launch.servlets.services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import utils.HttpServletRequestToEntityConverter;
import utils.RepositoryManager;

import javax.servlet.ServletContext;
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

    private String updatedSuccessfullyMessage;
    private Function<HttpServletRequest, T> updater;

    public UpdateEntityCommand(
            Class<T> clazz,
            ServletContext servlet,
            ShowList<T> showList,
            String updatedSuccessfullyMessage
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz), showList);
        logger.info("started construction");
        this.updatedSuccessfullyMessage = updatedSuccessfullyMessage;
        updater = new HttpServletRequestToEntityConverter<>(clazz);
        logger.info("constructed");
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        T entity = updater.apply(request);
        entity.getId().setValue(Long.valueOf(request.getParameter("id")));
        repository.save(entity);

        request.setAttribute("id", entity.getId().getValue());
        request.setAttribute("message", updatedSuccessfullyMessage);
        showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
