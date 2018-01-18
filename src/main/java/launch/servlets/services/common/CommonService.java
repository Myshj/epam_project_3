package launch.servlets.services.common;

import launch.servlets.services.ServletService;
import launch.servlets.services.common.commands.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;

/**
 * Service for functionality accessible for everyone.
 */
public class CommonService extends ServletService {
    private static final Logger logger = LogManager.getLogger(CommonService.class);

    public CommonService(ServletContext servlet) {
        super(servlet);
        logger.info("started construction");
        registerCommand("/common/search_showroom(/)*", new SearchShowroomById(servlet));
        registerCommand("/common/search_exposition(/)*", new SearchExpositionById(servlet));
        registerCommand("/common/search_ticket(/)*", new SearchTicketById(servlet));
        registerCommand("/common/show_purchase_form(/)*", new ShowPurchaseForm(this.servletContext));
        registerCommand("/common(/)*", new ShowMainPage(servlet));
        logger.info("constructed");
    }
}
