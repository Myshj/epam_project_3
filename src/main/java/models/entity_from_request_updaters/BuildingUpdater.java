package models.entity_from_request_updaters;

import models.Building;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class BuildingUpdater extends EntityFromRequestUpdater<Building> {
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
