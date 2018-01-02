package models.request_to_model_converters;

import models.Country;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

public class CountryConverter implements Function<HttpServletRequest, Country> {
    @Override
    public Country apply(HttpServletRequest request) {
        return new Country(request.getParameter("name"));
    }
}
