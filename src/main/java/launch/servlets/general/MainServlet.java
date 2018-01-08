package launch.servlets.general;

import launch.servlets.general.commands.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/general"}
)
public class MainServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchShowroomById", new SearchShowroomById(this));
        getActions.put("searchExpositionById", new SearchExpositionById(this));
        getActions.put("searchTicketById", new SearchTicketById(this));
        getActions.put("purchaseTicketById", new BeginPurchaseTicketById(this));
    }

    @Override
    protected ServletCommand defaultGetAction() {
        return new ShowMainPage(this);
    }

    @Override
    protected ServletCommand defaultPostAction() {
        return new ShowMainPage(this);
    }
}
