package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import launch.servlets.commands.SearchByNameAndCityNameAndCountryName;
import models.City;
import models.Street;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "StreetServlet",
        urlPatterns = {"/street"}
)
public class StreetServlet extends ModelServlet<Street> {

    private final Repository<City> cityRepository = RepositoryManager.INSTANCE.get(City.class);

    private final IncludeListToRequest<City> includeCities = new IncludeListToRequest<>(
            this,
            "cities"
    );

    private final SearchByNameAndCityNameAndCountryName<Street> searchByNameAndCityNameAndCountryName =
            new SearchByNameAndCityNameAndCountryName<>(
                    clazz(),
                    this,
                    repository,
                    forwardList
            );

    @Override
    protected String removedSuccessfullyMessage() {
        return "The street removed successfully.";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The street created successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The street removed successfully.";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchByNameAndCityNameAndCountryName", this::onSearchByNameAndCityNameAndCountryName);
    }

    @Override
    protected Class<Street> clazz() {
        return Street.class;
    }

    @Override
    protected String singularName() {
        return "street";
    }

    @Override
    protected String pluralName() {
        return "streets";
    }


    @Override
    protected void onSearchById(HttpServletRequest request, HttpServletResponse response) {
        try {
            includeCities.withList(cityRepository.getAll()).execute(request, response);
            super.onSearchById(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void onSearchByNameAndCityNameAndCountryName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            searchByNameAndCityNameAndCountryName.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeCities.withList(cityRepository.getAll()).execute(req, resp);
            super.onNewEntity(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
