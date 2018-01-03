package launch.servlets;

import launch.servlets.commands.SearchShowroomsByNameAndCityName;
import launch.servlets.commands.includers.IncludeAll;
import models.Building;
import models.Showroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

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
    protected String createdSuccessfullyMessage() {
        return "The showroom created successfully.";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The showroom removed successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The showroom updated successfully.";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                "searchShowroomsByNameAndCityName",
                new SearchShowroomsByNameAndCityName(clazz(), this, repository, forwardList)
        );

        IncludeAll<Building> includeBuildings = new IncludeAll<>(Building.class, this, "buildings");

        getActions.put("searchById", includeBuildings.andThen(getActions.get("searchById")));

        getActions.put("new", includeBuildings.andThen(getActions.get("new")));
    }
}
