package models.entity_from_request_updaters;

import models.Building;
import models.Showroom;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class ShowroomUpdater extends EntityFromRequestUpdater<Showroom> {
    @Override
    public void accept(Showroom showroom, HttpServletRequest request) {
        showroom.getName().setValue(request.getParameter("name"));
        showroom.getBuilding().setValue(
                RepositoryManager.INSTANCE.get(
                        Building.class
                ).getById(
                        Long.valueOf(request.getParameter("building"))
                ).orElse(null)
        );
    }
}
