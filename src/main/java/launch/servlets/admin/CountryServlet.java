package launch.servlets.admin;

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
}
