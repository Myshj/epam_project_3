package models.entity_from_request_updaters;

import models.City;
import models.Street;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

public class StreetUpdater implements BiConsumer<Street, HttpServletRequest> {
    @Override
    public void accept(Street street, HttpServletRequest request) {
        street.getName().setValue(
                request.getParameter("name")
        );
        street.getCity().setValue(
                RepositoryManager.INSTANCE.get(
                        City.class
                ).getById(
                        Long.valueOf(request.getParameter("city"))
                ).orElse(null)
        );
    }
}
