package launch.servlets.services.common;

import launch.servlets.ServiceContext;
import launch.servlets.services.ServletService;
import launch.servlets.services.common.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service for functionality accessible for everyone.
 */
public class CommonService extends ServletService {
    private static final Logger logger = LogManager.getLogger(CommonService.class);

    public CommonService(ServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand("/common/search_showroom(/)*", new SearchShowroomById(context));
        registerCommand("/common/search_exposition(/)*", new SearchExpositionById(context));
        registerCommand("/common/search_ticket(/)*", new SearchTicketById(context));
        registerCommand("/common/show_purchase_form(/)*", new ShowPurchaseForm(context));
        registerCommand("/common(/)*", new ShowMainPage(context));
        logger.info("constructed");
    }
}
