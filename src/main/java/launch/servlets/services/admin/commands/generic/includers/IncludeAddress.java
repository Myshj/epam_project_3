package launch.servlets.services.admin.commands.generic.includers;

import launch.servlets.services.admin.commands.generic.ModelCommand;
import models.Building;
import models.City;
import models.Country;
import models.Street;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Includes full address of building as request attribute.
 */
public class IncludeAddress extends ModelCommand<Building> {
    private static final Logger logger = LogManager.getLogger(IncludeAddress.class);

    private String name;
    private Building building;

    public IncludeAddress withBuilding(Building building) {
        logger.info("remembered building");
        this.building = building;
        return this;
    }

    public IncludeAddress(HttpServlet servlet, String name) {
        super(servlet, RepositoryManager.INSTANCE.get(Building.class));
        logger.info("created");
        this.name = name;
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        if (building == null) {
            logger.info("nothing to include");
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
        logger.info("included successfully");
    }
}
