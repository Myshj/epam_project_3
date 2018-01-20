package services.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletService;
import services.ServletServiceContext;
import services.common.commands.SearchExpositionById;
import services.common.commands.SearchShowroomById;
import services.common.commands.SearchTicketById;
import services.common.commands.ShowMainPage;

/**
 * Service for functionality accessible for everyone.
 */
public class CommonService extends ServletService {
    private static final Logger logger = LogManager.getLogger(CommonService.class);

    public CommonService(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        registerCommand(url("searchShowroom"), new SearchShowroomById(context));
        registerCommand(url("searchExposition"), new SearchExpositionById(context));
        registerCommand(url("searchTicket"), new SearchTicketById(context));
        registerCommand(url("mainPageTemplate"), new ShowMainPage(context));
        logger.info("constructed");
    }
}
