package launch.servlets;

import launch.servlets.commands.SearchExpositionsByNameAndShowroomName;
import launch.servlets.commands.includers.IncludeAll;
import models.Exposition;
import models.Showroom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "ExpositionServlet",
        urlPatterns = {"/exposition"}
)
public class ExpositionServlet extends ModelServlet<Exposition> {

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
    protected String removedSuccessfullyMessage() {
        return "The exposition removed successfully.";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The exposition created successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The exposition updated successfully.";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                "searchByNameAndShowroomName",
                new SearchExpositionsByNameAndShowroomName(clazz(), this, repository, forwardList)
        );

        IncludeAll<Showroom> includeShowrooms = new IncludeAll<>(Showroom.class, this, "showrooms");

        getActions.put(
                "searchById",
                includeShowrooms.andThen(getActions.get("searchById"))
        );

        getActions.put(
                "new",
                includeShowrooms.andThen(getActions.get("new"))
        );
    }
}
