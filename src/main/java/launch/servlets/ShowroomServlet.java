package launch.servlets;

import launch.servlets.commands.SearchShowroomsByNameAndCityName;
import models.Showroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "ShowroomServlet",
        urlPatterns = {"/admin/showroom"}
)
public class ShowroomServlet extends ModelServlet<Showroom> {
    private static String SEARCH_SHOWROOMS_BY_NAME_AND_CITY_NAME = action("searchShowroomsByNameAndCityName");

    @Override
    protected Class<Showroom> clazz() {
        return Showroom.class;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_SHOWROOMS_BY_NAME_AND_CITY_NAME,
                new SearchShowroomsByNameAndCityName(clazz(), this, repository, forwardList)
        );

//        IncludeAll<Building> includeBuildings = new IncludeAll<>(Building.class, this,
//                Model.pluralName(Building.class)
//        );
//
//        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeBuildings);
    }
}
