package services.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletService;
import services.ServletServiceContext;
import services.admin.commands.ShowMainAdminPage;
import utils.managers.MetaInfoManager;

/**
 * The main admin service.
 * Redirects requests to a child ModelAdminService or ShowMainAdminPage services.
 */
public class AdminService extends ServletService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    public AdminService(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        MetaInfoManager metaInfoManager = context.getManagers().getMetaInfo();
        metaInfoManager.classes().forEach(
                c -> registerCommand(
                        String.format(
                                url("adminEntityTemplate"),
                                meta(c).getNames().getSingular()
                        ),
                        new ModelAdminService(context, c)
                )
        );
        registerCommand(url("adminPattern"), new ShowMainAdminPage(context));
        logger.info("constructed");
    }
}
