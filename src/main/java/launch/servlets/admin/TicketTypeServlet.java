package launch.servlets.admin;

import models.TicketType;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "TicketTypeServlet",
        urlPatterns = {"/admin/ticket-type"}
)
public class TicketTypeServlet extends ModelServlet<TicketType> {
    @Override
    protected Class<TicketType> clazz() {
        return TicketType.class;
    }
}