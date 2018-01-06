package launch.servlets.admin;

import launch.servlets.admin.commands.SearchExpositionsByNameAndShowroomName;
import models.Exposition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "ExpositionServlet",
        urlPatterns = {"/admin/exposition"}
)
public class ExpositionServlet extends ModelServlet<Exposition> {
    private static String SEARCH_BY_NAME_AND_SHOWROOM_NAME = action("searchByNameAndShowroomName");

    @Override
    protected Class<Exposition> clazz() {
        return Exposition.class;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_BY_NAME_AND_SHOWROOM_NAME,
                new SearchExpositionsByNameAndShowroomName(clazz(), this, repository, forwardList)
        );
    }
}
