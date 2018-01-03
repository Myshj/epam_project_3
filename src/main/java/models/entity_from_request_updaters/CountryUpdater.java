package models.entity_from_request_updaters;

import models.Country;

import javax.servlet.http.HttpServletRequest;

public class CountryUpdater extends EntityFromRequestUpdater<Country> {
    @Override
    public void accept(Country country, HttpServletRequest request) {
        country.getName().setValue(
                request.getParameter("name")
        );
    }
}
