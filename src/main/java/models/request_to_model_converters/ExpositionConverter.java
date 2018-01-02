package models.request_to_model_converters;

import models.Exposition;
import models.Showroom;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class ExpositionConverter implements Function<HttpServletRequest, Exposition> {
    @Override
    public Exposition apply(HttpServletRequest request) {
        return new Exposition(
                request.getParameter("name"),
                RepositoryManager.INSTANCE.get(
                        Showroom.class
                ).getById(
                        Long.valueOf(request.getParameter("showroom"))
                ).orElse(null),
                LocalDate.parse(request.getParameter("begins")).atStartOfDay(),
                LocalDate.parse(request.getParameter("ends")).atStartOfDay()
        );
    }
}
