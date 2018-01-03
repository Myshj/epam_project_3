package launch.servlets;

import models.TicketType;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "TicketTypeServlet",
        urlPatterns = {"/ticket-type"}
)
public class TicketTypeServlet extends ModelServlet<TicketType> {
    @Override
    protected Class<TicketType> clazz() {
        return TicketType.class;
    }

    @Override
    protected String singularName() {
        return "ticketType";
    }

    @Override
    protected String pluralName() {
        return "ticketTypes";
    }
}
