package launch.servlets.services.common;

import launch.servlets.services.ServletService;
import launch.servlets.services.common.commands.*;

import javax.servlet.http.HttpServlet;

public class CommonService extends ServletService {
    public CommonService(HttpServlet servlet) {
        super(servlet);

        registerCommand("/common/search_showroom", new SearchShowroomById(servlet));
        registerCommand("/common/search_exposition", new SearchExpositionById(servlet));
        registerCommand("/common/search_ticket", new SearchTicketById(servlet));
        registerCommand("/common/show_purchase_form", new ShowPurchaseForm(this.servlet));
        registerCommand("/common", new ShowMainPage(servlet));
    }
}
