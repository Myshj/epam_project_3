package launch.servlets.general.commands;

import models.Ticket;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchTicketById extends ServletCommand {

    public SearchTicketById(HttpServlet servlet) {
        super(servlet);
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = RepositoryManager.INSTANCE.get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("ticket", ticket);
        dispatcher("/jsp/general/observe-ticket.jsp").forward(request, response);
    }
}
