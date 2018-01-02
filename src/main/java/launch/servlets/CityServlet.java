package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import launch.servlets.commands.SearchByNameAndCountryName;
import models.City;
import models.Country;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "CityServlet",
        urlPatterns = {"/city"}
)
public class CityServlet extends ModelServlet<City> {
    private final Repository<Country> countryRepository = RepositoryManager.INSTANCE.get(Country.class);

    private final IncludeListToRequest<Country> includeCountries = new IncludeListToRequest<>(
            this,
            "countries"
    );

    private final SearchByNameAndCountryName<City> searchByNameAndCountryName = new SearchByNameAndCountryName<>(
            clazz(),
            this,
            repository,
            forwardList
    );

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchByNameAndCountryName", this::searchByNameAndCountryName);
    }

    @Override
    protected Class<City> clazz() {
        return City.class;
    }

    @Override
    protected String singularName() {
        return "city";
    }

    @Override
    protected String pluralName() {
        return "cities";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The city created successfully.";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The city removed successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The city updated successfully.";
    }

    @Override
    protected void onSearchById(HttpServletRequest request, HttpServletResponse response) {
        try {
            includeCountries.withList(countryRepository.getAll()).execute(request, response);
            super.onSearchById(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void searchByNameAndCountryName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            searchByNameAndCountryName.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeCountries.withList(countryRepository.getAll()).execute(req, resp);
            super.onNewEntity(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
