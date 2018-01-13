package launch.servlets.services;

import launch.servlets.general.commands.*;

import javax.servlet.http.HttpServlet;

public class CommonService extends ServletService {
    public CommonService(HttpServlet servlet) {
        super(servlet);

        registerCommand("/common/search_showroom", new SearchShowroomById(this.servlet));
        registerCommand("/common/search_exposition", new SearchExpositionById(this.servlet));
        registerCommand("/common/search_ticket", new SearchTicketById(this.servlet));
        registerCommand("/common/purchase_ticket", new BeginPurchaseTicketById(this.servlet));
        registerCommand("/common", new ShowMainPage(this.servlet));
    }
}
