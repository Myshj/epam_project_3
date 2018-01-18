package launch.servlets.services.admin.commands.generic.includers;

import launch.servlets.services.admin.commands.generic.ModelCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Includes list of entities as request attribute.
 *
 * @param <T>
 */
public class IncludeListToRequest<T extends Model> extends ModelCommand<T> {
    private static final Logger logger = LogManager.getLogger(IncludeListToRequest.class);
    private String name;
    private List<T> list;

    public IncludeListToRequest(
            ServletContext servlet,
            String name
    ) {
        super(servlet, null);
        logger.info("created");
        this.name = name;
    }

    public IncludeListToRequest<T> withList(List<T> list) {
        logger.info("remembered list");
        this.list = list;
        return this;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
                name,
                list
        );
    }
}
