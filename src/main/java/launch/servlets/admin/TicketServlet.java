package launch.servlets.admin;

import models.Ticket;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "TicketServlet",
        urlPatterns = {"/admin/ticket"}
)
public class TicketServlet extends ModelServlet<Ticket> {

    @Override
    protected Class<Ticket> clazz() {
        return Ticket.class;
    }
}
