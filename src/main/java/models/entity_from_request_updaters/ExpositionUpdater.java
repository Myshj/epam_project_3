package models.entity_from_request_updaters;

import models.Exposition;
import models.Showroom;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.function.BiConsumer;

public class ExpositionUpdater implements BiConsumer<Exposition, HttpServletRequest> {
    @Override
    public void accept(Exposition exposition, HttpServletRequest request) {
        exposition.getName().setValue(request.getParameter("name"));
        exposition.getPlace().setValue(
                RepositoryManager.INSTANCE.get(
                        Showroom.class
                ).getById(
                        Long.valueOf(request.getParameter("place")
                        )
                ).orElse(null)
        );
        exposition.getBegins().setValue(
                LocalDate.parse(request.getParameter("begins")).atStartOfDay()
        );
        exposition.getEnds().setValue(
                LocalDate.parse(request.getParameter("ends")).atStartOfDay()
        );
    }
}
