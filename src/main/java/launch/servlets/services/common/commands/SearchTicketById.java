package launch.servlets.services.common.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.admin.commands.generic.includers.IncludeAddress;
import launch.servlets.services.commands.ServletCommand;
import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


        dispatcher("/jsp/general/observe-ticket.jsp").forward(request, response);
        logger.info("executed");
    }

    public SearchTicketById(ServiceContext context) {
        super(context);
        addressIncluder = new IncludeAddress(context);
        logger.info("constructed");
    }
}
