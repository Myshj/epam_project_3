package launch.servlets;

import launch.servlets.commands.SearchByNameAndCityNameAndCountryName;
import launch.servlets.commands.includers.IncludeAll;
import models.City;
import models.Street;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "StreetServlet",
        urlPatterns = {"/street"}
)
public class StreetServlet extends ModelServlet<Street> {

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
        getActions.put(
                "searchByNameAndCityNameAndCountryName",
                new SearchByNameAndCityNameAndCountryName<>(clazz(), this, repository, forwardList)
        );

        IncludeAll<City> includeCities = new IncludeAll<>(City.class, this, "cities");

        getActions.put("searchById", includeCities.andThen(getActions.get("searchById")));

        getActions.put("new", includeCities.andThen(getActions.get("new")));
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
}
