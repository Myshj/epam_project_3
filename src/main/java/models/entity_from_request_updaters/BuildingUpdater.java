package models.entity_from_request_updaters;

import models.Building;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

public class BuildingUpdater implements BiConsumer<Building, HttpServletRequest> {
    @Override
    public void accept(Building building, HttpServletRequest request) {
        building.getName().setValue(
                request.getParameter("name")
        );
        building.getStreet().setValue(
                RepositoryManager.INSTANCE.get(
                        Street.class
                ).getById(
                        Long.valueOf(request.getParameter("street")
                        )
                ).orElse(null)
        );
    }
}
