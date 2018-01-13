package launch.servlets.services.common.commands;

import launch.servlets.services.commands.ServletCommand;
import models.Ticket;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowPurchaseForm extends ServletCommand {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = RepositoryManager.INSTANCE.get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("ticket", ticket);
        dispatcher("/jsp/general/purchase-ticket.jsp").forward(request, response);
    }

    public ShowPurchaseForm(HttpServlet servlet) {
        super(servlet);
    }
}
