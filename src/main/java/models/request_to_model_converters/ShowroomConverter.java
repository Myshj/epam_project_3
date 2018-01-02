package models.request_to_model_converters;

import models.Building;
import models.Showroom;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

public class ShowroomConverter implements Function<HttpServletRequest, Showroom> {
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
