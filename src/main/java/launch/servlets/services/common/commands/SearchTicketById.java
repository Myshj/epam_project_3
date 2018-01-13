package launch.servlets.services.common.commands;

import launch.servlets.services.admin.commands.generic.includers.IncludeAddress;
import launch.servlets.services.commands.ServletCommand;
import models.Ticket;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchTicketById extends ServletCommand {
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
        dispatcher("/jsp/general/observe-ticket.jsp").forward(request, response);
    }

    public SearchTicketById(HttpServlet servlet) {
        super(servlet);
    }
}
