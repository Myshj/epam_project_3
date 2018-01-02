package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import launch.servlets.commands.SearchShowroomsByNameAndCityName;
import models.Building;
import models.Showroom;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ShowroomServlet",
        urlPatterns = {"/showroom"}
)
public class ShowroomServlet extends ModelServlet<Showroom> {

    private final Repository<Building> buildingRepository = RepositoryManager.INSTANCE.get(Building.class);

    private final IncludeListToRequest<Building> includeBuildings = new IncludeListToRequest<>(
            this,
            "buildings"
    );

    private final SearchShowroomsByNameAndCityName searchShowroomsByNameAndCityName =
            new SearchShowroomsByNameAndCityName(
                    clazz(),
                    this,
                    repository,
                    forwardList
            );

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
        getActions.put("searchShowroomsByNameAndCityName", this::onSearchByNameAndCityName);
    }

    @Override
    protected void onSearchById(HttpServletRequest request, HttpServletResponse response) {
        try {
            includeBuildings.withList(buildingRepository.getAll()).execute(request, response);
            super.onSearchById(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void onSearchByNameAndCityName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            searchShowroomsByNameAndCityName.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeBuildings.withList(buildingRepository.getAll()).execute(req, resp);
            super.onNewEntity(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
