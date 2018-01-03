package models.request_to_model_converters;

import models.City;
import models.Country;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class CityConverter extends RequestToModelConverter<City> {
    @Override
    public City apply(HttpServletRequest request) {
        return new City(
                request.getParameter("name"),
                RepositoryManager.INSTANCE.get(
                        Country.class
                ).getById(
                        Long.valueOf(request.getParameter("country"))
                ).orElse(null)
        );
    }
}
