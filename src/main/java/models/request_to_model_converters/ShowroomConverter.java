package models.request_to_model_converters;

import models.Building;
import models.Showroom;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class ShowroomConverter extends RequestToModelConverter<Showroom> {
    @Override
    public Showroom apply(HttpServletRequest request) {
        return new Showroom(
                request.getParameter("name"),
                RepositoryManager.INSTANCE.get(
                        Building.class
                ).getById(
                        Long.valueOf(request.getParameter("building"))
                ).orElse(null)
        );
    }
}
