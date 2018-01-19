package launch.servlets.services.admin.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Displays the main admin page
 */
public class ShowMainAdminPage extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ShowMainAdminPage.class);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        request.setAttribute(
                "metaInfos",
                MetaInfoManager.INSTANCE.classes().stream()
                        .map(MetaInfoManager.INSTANCE::get)
                        .collect(Collectors.toList())
        );
        dispatcher("/jsp/admin/main.jsp").forward(request, response);
        logger.info("executed");
    }

    public ShowMainAdminPage(ServiceContext context) {
        super(context);
        logger.info("constructed");
    }
}
