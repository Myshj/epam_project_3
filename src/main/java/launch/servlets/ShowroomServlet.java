package launch.servlets;

import launch.servlets.commands.SearchShowroomsByNameAndCityName;
import launch.servlets.commands.generic.includers.IncludeAll;
import models.Building;
import models.Showroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

@WebServlet(
        name = "ShowroomServlet",
        urlPatterns = {"/showroom"}
)
public class ShowroomServlet extends ModelServlet<Showroom> {

    @Override
    protected Class<Showroom> clazz() {
        return Showroom.class;
    }

    @Override
    protected String singularName() {
        return "showroom";
    }

    @Override
    protected String pluralName() {
        return "showrooms";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                "searchShowroomsByNameAndCityName",
                new SearchShowroomsByNameAndCityName(clazz(), this, repository, forwardList)
        );

        IncludeAll<Building> includeBuildings = new IncludeAll<>(Building.class, this, "buildings");

        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeBuildings);
    }
}
