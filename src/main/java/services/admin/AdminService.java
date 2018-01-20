package services.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletService;
import services.ServletServiceContext;
import services.admin.commands.ShowMainAdminPage;
import utils.meta.MetaInfoManager;

/**
 * The main admin service.
 * Redirects requests to a child ModelAdminService or ShowMainAdminPage services.
 */
public class AdminService extends ServletService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    public AdminService(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        MetaInfoManager.INSTANCE.classes().forEach(
                c -> registerCommand(
                        String.format(
                                url("adminEntityTemplate"),
                                MetaInfoManager.INSTANCE.get(c).getNames().getSingular()
                        ),
                        new ModelAdminService(context, c)
                )
        );
        registerCommand(url("adminPattern"), new ShowMainAdminPage(context));
        logger.info("constructed");
    }
}
