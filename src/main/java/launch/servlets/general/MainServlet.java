package launch.servlets.general;

import launch.servlets.general.commands.BeginPurchaseTicketById;
import launch.servlets.general.commands.SearchExpositionById;
import launch.servlets.general.commands.SearchShowroomById;
import launch.servlets.general.commands.SearchTicketById;

import javax.servlet.ServletException;

//@WebServlet(
//        name = "MainServlet",
//        urlPatterns = {"/general"}
//)
public class MainServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchShowroomById", new SearchShowroomById(this));
        getActions.put("searchExpositionById", new SearchExpositionById(this));
        getActions.put("searchTicketById", new SearchTicketById(this));
        getActions.put("purchaseTicketById", new BeginPurchaseTicketById(this));
    }
}
