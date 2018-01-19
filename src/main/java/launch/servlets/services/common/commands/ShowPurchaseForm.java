package launch.servlets.services.common.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.commands.ServletCommand;
import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Show form for ticket purchase.
 */
public class ShowPurchaseForm extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ShowPurchaseForm.class);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        Ticket ticket = context.getManagers().getRepository().get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("ticket", ticket);
        dispatcher("/jsp/general/purchase-ticket.jsp").forward(request, response);
        logger.info("executed");
    }

    public ShowPurchaseForm(ServiceContext context) {
        super(context);
        logger.info("constructed");
    }
}
