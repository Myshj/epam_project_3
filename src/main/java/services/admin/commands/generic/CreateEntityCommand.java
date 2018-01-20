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
 * Creates entity in database from request parameters.
 *
 * @param <T>
 */
public class CreateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private static final Logger logger = LogManager.getLogger(CreateEntityCommand.class);
    private Function<HttpServletRequest, T> converter;

    public CreateEntityCommand(
            ServletServiceContext context,
            Class<T> clazz,
            ShowList<T> showList
    ) {
        super(
                context,
                clazz,
                showList
        );
        logger.info("started construction");
        this.converter = new HttpServletRequestToEntityConverter<>(context, clazz);
        logger.info("constructed");
    }

    @Override
    public final void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        T newEntity = converter.apply(request);
        repository.save(newEntity);
        request.setAttribute("id", newEntity.getId().get().orElse(null));
        showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
