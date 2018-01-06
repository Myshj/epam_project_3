package launch.servlets.admin;

import launch.servlets.admin.commands.SearchCitiesByNameAndCountryName;
import models.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "CityServlet",
        urlPatterns = {"/admin/city"}
)
public class CityServlet extends ModelServlet<City> {
    private static String SEARCH_BY_NAME_AND_COUNTRY_NAME = action("searchByNameAndCountryName");

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_BY_NAME_AND_COUNTRY_NAME,
                new SearchCitiesByNameAndCountryName<>(clazz(), this, repository, forwardList)
        );
    }

    @Override
    protected Class<City> clazz() {
        return City.class;
    }
}
