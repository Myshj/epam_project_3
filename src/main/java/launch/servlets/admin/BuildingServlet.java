package launch.servlets.admin;

import launch.servlets.admin.commands.SearchBuildingsByNameAndStreetNameAndCityNameAndCountryName;
import models.Building;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "BuildingServlet",
        urlPatterns = {"/admin/building"}
)
public class BuildingServlet extends ModelServlet<Building> {
    private static String SEARCH_BY_NAME_AND_STREET_NAME_AND_CITY_NAME_AND_COUNTRY_NAME =
            action("searchByNameAndStreetNameAndCityNameAndCountryName");

    @Override
    protected Class<Building> clazz() {
        return Building.class;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put(
                SEARCH_BY_NAME_AND_STREET_NAME_AND_CITY_NAME_AND_COUNTRY_NAME,
                new SearchBuildingsByNameAndStreetNameAndCityNameAndCountryName<>(clazz(), this, repository, forwardList)
        );
    }
}
