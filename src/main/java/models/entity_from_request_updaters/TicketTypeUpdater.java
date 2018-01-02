package models.entity_from_request_updaters;

import models.TicketType;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

public class TicketTypeUpdater implements BiConsumer<TicketType, HttpServletRequest> {
    @Override
    public void accept(TicketType type, HttpServletRequest request) {
        type.getName().setValue(
                request.getParameter("name")
        );
    }
}
