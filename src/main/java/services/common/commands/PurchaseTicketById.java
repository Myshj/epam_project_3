package services.common.commands;

import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PurchaseTicketById extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(PurchaseTicketById.class);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        Ticket ticket = context.getManagers().getRepository().get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);

        if (!isAvailable(ticket)) {
            logger.error("ticket unavailable or does not exist --> doing nothing");
            dispatcher(url("error500Jsp")).forward(request, response);
            return;
        }


    }

    private boolean isAvailable(Ticket ticket) {
        return ticket != null && ticket.getAvailable().getValue();
    }

    public PurchaseTicketById(ServletServiceContext context) {
        super(context);
    }
}
