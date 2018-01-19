package launch.servlets.services.admin.commands.generic.includers;

import launch.servlets.ServiceContext;
import launch.servlets.services.admin.commands.generic.ModelCommand;
import models.Building;
import models.City;
import models.Country;
import models.Street;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Includes full address of building as request attribute.
 */
public class IncludeAddress extends ModelCommand<Building> {
    private static final Logger logger = LogManager.getLogger(IncludeAddress.class);

    private Building building;

    public IncludeAddress withBuilding(Building building) {
        logger.info("remembered building");
        this.building = building;
        return this;
    }

    public IncludeAddress(ServiceContext context) {
        super(context, Building.class);
        logger.info("created");
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
