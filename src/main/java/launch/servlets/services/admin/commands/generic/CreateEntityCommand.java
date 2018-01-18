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
 * Creates entity in database from request parameters.
 *
 * @param <T>
 */
public class CreateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private static final Logger logger = LogManager.getLogger(CreateEntityCommand.class);
    private String createdSuccessfullyMessage;
    private Function<HttpServletRequest, T> converter;

    public CreateEntityCommand(
            Class<T> clazz,
            ServletContext servlet,
            ShowList<T> showList,
            String createdSuccessfullyMessage
    ) {
        super(
                servlet,
                RepositoryManager.INSTANCE.get(clazz),
                showList
        );
        logger.info("started construction");
        this.createdSuccessfullyMessage = createdSuccessfullyMessage;
        this.converter = new HttpServletRequestToEntityConverter<>(clazz);
        logger.info("constructed");
    }

    @Override
    public final void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        T newEntity = converter.apply(request);
        repository.save(newEntity);
        request.setAttribute("id", newEntity.getId().get().orElse(null));
        request.setAttribute("message", createdSuccessfullyMessage);
        showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
