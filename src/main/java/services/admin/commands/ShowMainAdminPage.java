package services.admin.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletServiceContext;
import services.commands.ServletCommand;

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
                context.getManagers().getMetaInfo().classes().stream()
                        .map(this::meta)
                        .collect(Collectors.toList())
        );
        dispatcher(
                url("adminMain")
        ).forward(request, response);
        logger.info("executed");
    }

    public ShowMainAdminPage(ServletServiceContext context) {
        super(context);
        logger.info("constructed");
    }
}
