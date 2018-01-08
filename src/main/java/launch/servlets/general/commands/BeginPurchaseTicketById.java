package launch.servlets.general.commands;

import launch.servlets.admin.commands.generic.includers.IncludeAddress;
import models.Ticket;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BeginPurchaseTicketById extends ServletCommand {
    private IncludeAddress addressIncluder = new IncludeAddress(this.servlet, "address");

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = RepositoryManager.INSTANCE.get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("ticket", ticket);
        if (ticket != null) {
            addressIncluder.withBuilding(
                    ticket.getExposition().getValue().getPlace().getValue().getBuilding().getValue()
            ).accept(request, response);
        }
        dispatcher("/jsp/general/purchase-ticket.jsp").forward(request, response);
    }

    public BeginPurchaseTicketById(HttpServlet servlet) {
        super(servlet);
    }
}
