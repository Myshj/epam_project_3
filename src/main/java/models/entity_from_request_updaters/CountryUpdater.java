package models.entity_from_request_updaters;

import models.Country;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

public class CountryUpdater implements BiConsumer<Country, HttpServletRequest> {
    @Override
    public void accept(Country country, HttpServletRequest request) {
        country.getName().setValue(
                request.getParameter("name")
        );
    }
}
