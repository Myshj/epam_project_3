package launch.servlets;

import launch.servlets.commands.SearchByNameAndStreetNameAndCityNameAndCountryName;
import launch.servlets.commands.includers.IncludeAll;
import models.Building;
import models.Street;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "BuildingServlet",
        urlPatterns = {"/building"}
)
public class BuildingServlet extends ModelServlet<Building> {


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
                new SearchByNameAndStreetNameAndCityNameAndCountryName<>(
                        clazz(),
                        this,
                        repository,
                        forwardList
                )
        );

        IncludeAll<Street> includeStreets = new IncludeAll<>(
                Street.class,
                this,
                "streets"
        );

        getActions.put(
                "searchById",
                includeStreets.andThen(getActions.get("searchById"))
        );

        getActions.put(
                "new",
                includeStreets.andThen(getActions.get("new"))
        );
    }
}
