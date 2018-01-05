package launch.servlets;

import models.Country;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "CountryServlet",
        urlPatterns = {"/admin/country"}
)
public class CountryServlet extends ModelServlet<Country> {

    @Override
    protected Class<Country> clazz() {
        return Country.class;
    }

    @Override
    protected String singularName() {
        return "country";
    }

    @Override
    protected String pluralName() {
        return "countries";
    }
}
