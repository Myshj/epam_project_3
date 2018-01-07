package launch.servlets.general;

import launch.servlets.general.commands.SearchShowroomById;
import launch.servlets.general.commands.ServletCommand;
import launch.servlets.general.commands.ShowMainPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "MainServlet",
        urlPatterns = {"/general", "/general/showroom"}
)
public class MainServlet extends MyServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchById", new SearchShowroomById(this));
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
