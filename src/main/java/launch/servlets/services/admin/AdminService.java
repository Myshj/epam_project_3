package launch.servlets.services.admin;

import launch.servlets.services.ServletService;
import launch.servlets.services.admin.commands.ShowMainAdminPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletContext;

/**
 * The main admin service.
 * Redirects requests to a child ModelAdminService or ShowMainAdminPage services.
 */
public class AdminService extends ServletService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    public AdminService(ServletContext servlet) {
        super(servlet);
        logger.info("started construction");
        MetaInfoManager.INSTANCE.classes().forEach(
                c -> registerCommand(
                        String.format(
                                "/admin/%s/.*",
                                MetaInfoManager.INSTANCE.get(c).getNames().getSingular()
                        ),
                        new ModelAdminService(servlet, c)
                )
        );
        registerCommand("/admin(/)*", new ShowMainAdminPage(servlet));
        logger.info("constructed");
    }
}
