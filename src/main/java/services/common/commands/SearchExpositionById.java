package services.common.commands;

import data_access.queries.FindTicketsByExposition;
import models.Exposition;
import models.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.impl.sql.queries.SqlQueryContext;
import services.ServletServiceContext;
import services.commands.ServletCommand;
import services.commands.includers.IncludeListToRequest;
import utils.transactions.TransactionExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command to search exposition and related tickets by given id of exposition.
 */
public class SearchExpositionById extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(SearchExpositionById.class);

    private FindTicketsByExposition ticketsFinder;
    private IncludeListToRequest<Ticket> ticketIncluder = new IncludeListToRequest<>(
            context, Ticket.class,
            meta(Ticket.class).getNames().getPlural()
    );

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        new TransactionExecutor(context.getManagers().getConnection().get()).apply(
                () -> {
                    Exposition exposition = context.getManagers().getRepository().get(Exposition.class).getById(
                            Long.valueOf(request.getParameter("id"))
                    ).orElse(null);
                    request.setAttribute("exposition", exposition);

                    if (exposition != null) {
                        ticketIncluder.withList(ticketsFinder.withExposition(exposition).get())
                                .accept(request, response);
                    }
                }
        );


        dispatcher(
                url("observeExpositionJsp")
        ).forward(request, response);
        logger.info("executed");
    }

    public SearchExpositionById(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        ticketsFinder = new FindTicketsByExposition(
                new SqlQueryContext<>(
                        Ticket.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
                //ConnectionServiceProvider.INSTANCE.get()
        );
        logger.info("constructed");
    }
}
