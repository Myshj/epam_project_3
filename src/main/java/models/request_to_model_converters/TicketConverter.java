package models.request_to_model_converters;

import models.Exposition;
import models.Ticket;
import models.TicketType;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class TicketConverter extends RequestToModelConverter<Ticket> {
    @Override
    public Ticket apply(HttpServletRequest request) {
        return new Ticket(
                RepositoryManager.INSTANCE.get(Exposition.class).getById(
                        Long.valueOf(request.getParameter("exposition"))
                ).orElse(null),
                RepositoryManager.INSTANCE.get(TicketType.class).getById(
                        Long.valueOf(request.getParameter("type"))
                ).orElse(null),
                BigDecimal.valueOf(
                        Double.valueOf(request.getParameter("price"))
                )
        );
    }
}
