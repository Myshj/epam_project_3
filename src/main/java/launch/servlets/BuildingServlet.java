package launch.servlets;

import launch.servlets.commands.SearchBuildingsByNameAndStreetNameAndCityNameAndCountryName;
import launch.servlets.commands.generic.includers.IncludeAll;
import models.Building;
import models.Street;
import orm.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

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

        IncludeAll<Street> includeStreets = new IncludeAll<>(Street.class, this, Model.pluralName(Street.class));
        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeStreets);
    }
}
