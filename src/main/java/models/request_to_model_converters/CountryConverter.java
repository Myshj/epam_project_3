package models.request_to_model_converters;

import models.Country;

import javax.servlet.http.HttpServletRequest;

public class CountryConverter extends RequestToModelConverter<Country> {
    @Override
    public Country apply(HttpServletRequest request) {
        return new Country(request.getParameter("name"));
    }
}
