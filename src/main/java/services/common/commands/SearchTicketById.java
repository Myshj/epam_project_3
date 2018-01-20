package services.common.commands;

import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletServiceContext;
import services.admin.commands.generic.includers.IncludeAddress;
import services.commands.ServletCommand;
import utils.transactions.TransactionExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to search a ticket and related showroom address by a given ticket id.
 */
public class SearchTicketById extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(SearchTicketById.class);

    private IncludeAddress addressIncluder;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");

        new TransactionExecutor(context.getManagers().getConnection().get()).apply(
                () -> {
                    Ticket ticket = context.getManagers().getRepository().get(Ticket.class).getById(
                            Long.valueOf(request.getParameter("id"))
                    ).orElse(null);
                    request.setAttribute("ticket", ticket);
                    if (ticket != null) {
                        addressIncluder.withBuilding(
                                ticket.getExposition().getValue().getPlace().getValue().getBuilding().getValue()
                        ).accept(request, response);
                    }
                }
        );


        dispatcher(url("observeTicketJsp")).forward(request, response);
        logger.info("executed");
    }

    public SearchTicketById(ServletServiceContext context) {
        super(context);
        addressIncluder = new IncludeAddress(context);
        logger.info("constructed");
    }
}
