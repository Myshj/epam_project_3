package launch.servlets.admin.commands.generic.includers;

import launch.servlets.admin.commands.generic.ModelCommand;
import models.Building;
import models.City;
import models.Country;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IncludeAddress extends ModelCommand<Building> {
    private String name;
    private Building building;

    public IncludeAddress withBuilding(Building building) {
        this.building = building;
        return this;
    }

    public IncludeAddress(HttpServlet servlet, String name) {
        super(servlet, RepositoryManager.INSTANCE.get(Building.class));
        this.name = name;
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (building == null) {
            return;
        }

        Street street = building.getStreet().getValue();
        City city = street.getCity().getValue();
        Country country = city.getCountry().getValue();
        request.setAttribute(
                "address",
                String.format(
                        "%s, %s, %s, %s", country.getName(), city.getName(), street.getName(), building.getName()
                )
        );
    }
}
