package launch.servlets.admin;

import launch.servlets.admin.commands.SearchStreetsByNameAndCityNameAndCountryName;
import models.Street;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

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
    }

    @Override
    protected Class<Street> clazz() {
        return Street.class;
    }
}
