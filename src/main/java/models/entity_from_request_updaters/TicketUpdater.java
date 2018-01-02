package models.entity_from_request_updaters;

import models.Exposition;
import models.Ticket;
import models.TicketType;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.function.BiConsumer;

public class TicketUpdater implements BiConsumer<Ticket, HttpServletRequest> {
    @Override
    public void accept(Ticket ticket, HttpServletRequest request) {
        ticket.getExposition().setValue(
                RepositoryManager.INSTANCE.get(Exposition.class).getById(
                        Long.valueOf(request.getParameter("exposition"))
                ).orElse(null)
        );
        ticket.getType().setValue(
                RepositoryManager.INSTANCE.get(TicketType.class).getById(
                        Long.valueOf(request.getParameter("type"))
                ).orElse(null)
        );
        ticket.getPrice().setValue(
                BigDecimal.valueOf(
                        Double.valueOf(request.getParameter("price"))
                )
        );
    }
}
