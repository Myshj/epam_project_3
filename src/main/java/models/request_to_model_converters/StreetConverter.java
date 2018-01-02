package models.request_to_model_converters;

import models.City;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

public class StreetConverter implements Function<HttpServletRequest, Street> {
    @Override
    public Street apply(HttpServletRequest request) {
        return new Street(
                request.getParameter("name"),
                RepositoryManager.INSTANCE.get(
                        City.class
                ).getById(
                        Long.valueOf(request.getParameter("city"))
                ).orElse(null)
        );
    }
}
