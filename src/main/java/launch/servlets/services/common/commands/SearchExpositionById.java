package launch.servlets.services.common.commands;

import launch.servlets.services.admin.commands.generic.includers.IncludeListToRequest;
import launch.servlets.services.commands.ServletCommand;
import models.Exposition;
import models.Ticket;
import models.commands.FindTicketsByExposition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionManager;
import utils.RepositoryManager;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Command to search exposition and related tickets by given id of exposition.
 */
public class SearchExpositionById extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(SearchExpositionById.class);

    private FindTicketsByExposition ticketsFinder;
    private IncludeListToRequest<Ticket> ticketIncluder = new IncludeListToRequest<>(
            this.servlet, MetaInfoManager.INSTANCE.get(Ticket.class).getNames().getPlural()
    );

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        Exposition exposition = RepositoryManager.INSTANCE.get(Exposition.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("exposition", exposition);

        if (exposition != null) {
            ticketIncluder.withList(ticketsFinder.withExposition(exposition).execute())
                    .accept(request, response);
        }

        dispatcher("/jsp/general/observe-exposition.jsp").forward(request, response);
        logger.info("executed");
    }

    public SearchExpositionById(HttpServlet servlet) {
        super(servlet);
        logger.info("started construction");
        try {
            ticketsFinder = new FindTicketsByExposition(Ticket.class, ConnectionManager.INSTANCE.get());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }
        logger.info("constructed");
    }
}
