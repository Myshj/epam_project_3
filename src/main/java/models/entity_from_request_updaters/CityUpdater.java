package models.entity_from_request_updaters;

import models.City;
import models.Country;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class CityUpdater extends EntityFromRequestUpdater<City> {
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
