package launch.servlets;

import launch.servlets.commands.SearchExpositionsByNameAndShowroomName;
import launch.servlets.commands.generic.includers.IncludeAll;
import models.Exposition;
import models.Showroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

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
    protected String singularName() {
        return "exposition";
    }

    @Override
    protected String pluralName() {
        return "expositions";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_BY_NAME_AND_SHOWROOM_NAME,
                new SearchExpositionsByNameAndShowroomName(clazz(), this, repository, forwardList)
        );

        IncludeAll<Showroom> includeShowrooms = new IncludeAll<>(Showroom.class, this, "showrooms");
        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeShowrooms);
    }
}
