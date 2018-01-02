package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import launch.servlets.commands.SearchByNameAndStreetNameAndCityNameAndCountryName;
import models.Building;
import models.Street;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "BuildingServlet",
        urlPatterns = {"/building"}
)
public class BuildingServlet extends ModelServlet<Building> {

    private final Repository<Street> streetRepository = RepositoryManager.INSTANCE.get(Street.class);

    private final IncludeListToRequest<Street> includeStreets = new IncludeListToRequest<>(
            this,
            "streets"
    );

    private final SearchByNameAndStreetNameAndCityNameAndCountryName<Building> searchByNameAndStreetNameAndCityNameAndCountryName =
            new SearchByNameAndStreetNameAndCityNameAndCountryName<>(
                    clazz(),
                    this,
                    repository,
                    forwardList
            );

    @Override
    protected Class<Building> clazz() {
        return Building.class;
    }

    @Override
    protected String singularName() {
        return "building";
    }

    @Override
    protected String pluralName() {
        return "buildings";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The building created successfully.";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The building removed successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The building updated successfully.";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                "searchByNameAndStreetNameAndCityNameAndCountryName",
                this::onSearchByNameAndStreetNameAndCityNameAndCountryName
        );
    }

    @Override
    protected void onSearchById(HttpServletRequest request, HttpServletResponse response) {
        try {
            includeStreets.withList(streetRepository.getAll()).execute(request, response);
            super.onSearchById(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void onSearchByNameAndStreetNameAndCityNameAndCountryName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            searchByNameAndStreetNameAndCityNameAndCountryName.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeStreets.withList(streetRepository.getAll()).execute(req, resp);
            super.onNewEntity(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
