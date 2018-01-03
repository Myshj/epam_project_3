package models.request_to_model_converters;

import models.Building;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class BuildingConverter extends RequestToModelConverter<Building> {
    @Override
    public Building apply(HttpServletRequest request) {
        return new Building(
                request.getParameter("name"),
                RepositoryManager.INSTANCE.get(
                        Street.class
                ).getById(
                        Long.valueOf(request.getParameter("street"))
                ).orElse(null)
        );
    }
}
