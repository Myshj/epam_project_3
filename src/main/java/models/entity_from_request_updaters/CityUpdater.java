package models.entity_from_request_updaters;

import models.City;
import models.Country;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

public class CityUpdater implements BiConsumer<City, HttpServletRequest> {
    @Override
    public void accept(City city, HttpServletRequest request) {
        city.getName().setValue(
                request.getParameter("name")
        );
        city.getCountry().setValue(
                RepositoryManager.INSTANCE.get(
                        Country.class
                ).getById(
                        Long.valueOf(request.getParameter("country"))
                ).orElse(null)
        );
    }
}
