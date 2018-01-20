package services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletServiceContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Removes entity specified by request parameter id from database.
 *
 * @param <T>
 */
public class RemoveEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private static final Logger logger = LogManager.getLogger(RemoveEntityCommand.class);

    public RemoveEntityCommand(
            ServletServiceContext context,
            Class<T> clazz,
            ShowList<T> showList
    ) {
        super(context, clazz, showList);
        logger.info("constructed");
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        logger.info("started execution");
        repository.delete(
                repository.getById(
                        Long.valueOf(request.getParameter("id"))
                ).orElse(null)
        );
        showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
