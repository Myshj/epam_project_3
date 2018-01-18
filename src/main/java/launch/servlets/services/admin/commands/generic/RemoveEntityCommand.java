package launch.servlets.services.admin.commands.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletContext;
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

    private String message;

    public RemoveEntityCommand(
            ServletContext servlet,
            Repository<T> repository,
            ShowList<T> showList,
            String message
    ) {
        super(servlet, repository, showList);
        logger.info("constructed");
        this.message = message;
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
        request.setAttribute("message", message);
        showList.withList(repository.getAll()).execute(request, response);
        logger.info("executed");
    }
}
