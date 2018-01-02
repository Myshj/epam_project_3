package launch.servlets;

import models.Country;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "CountryServlet",
        urlPatterns = {"/country"}
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

    @Override
    protected String createdSuccessfullyMessage() {
        return "The country created successfully.";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The country removed successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The country updated successfully.";
    }
}
