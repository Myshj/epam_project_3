package launch.servlets;

import launch.servlets.commands.SearchByNameAndCountryName;
import launch.servlets.commands.includers.IncludeAll;
import models.City;
import models.Country;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "CityServlet",
        urlPatterns = {"/city"}
)
public class CityServlet extends ModelServlet<City> {

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                "searchByNameAndCountryName",
                new SearchByNameAndCountryName<>(
                        clazz(),
                        this,
                        repository,
                        forwardList
                )
        );

        IncludeAll<Country> includeCountries = new IncludeAll<>(
                Country.class,
                this,
                "countries"
        );

        getActions.put(
                "searchById",
                includeCountries.andThen(getActions.get("searchById"))
        );
        getActions.put(
                "new",
                includeCountries.andThen(getActions.get("new"))
        );
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
}
