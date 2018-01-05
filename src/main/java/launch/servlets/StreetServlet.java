package launch.servlets;

import launch.servlets.commands.SearchStreetsByNameAndCityNameAndCountryName;
import launch.servlets.commands.generic.includers.IncludeAll;
import models.City;
import models.Street;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

@WebServlet(
        name = "StreetServlet",
        urlPatterns = {"/admin/street"}
)
public class StreetServlet extends ModelServlet<Street> {
    private static String SEARCH_STREETS_BY_NAME_AND_CITY_NAME_AND_COUNTRY_NAME =
            action("searchStreetsByNameAndCityNameAndCountryName");

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_STREETS_BY_NAME_AND_CITY_NAME_AND_COUNTRY_NAME,
                new SearchStreetsByNameAndCityNameAndCountryName<>(clazz(), this, repository, forwardList)
        );

        IncludeAll<City> includeCities = new IncludeAll<>(City.class, this, "cities");

        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeCities);
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
