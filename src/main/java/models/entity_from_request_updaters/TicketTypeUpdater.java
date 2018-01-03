package models.entity_from_request_updaters;

import models.TicketType;

import javax.servlet.http.HttpServletRequest;

public class TicketTypeUpdater extends EntityFromRequestUpdater<TicketType> {
    @Override
    public void accept(TicketType type, HttpServletRequest request) {
        type.getName().setValue(
                request.getParameter("name")
        );
    }
}
