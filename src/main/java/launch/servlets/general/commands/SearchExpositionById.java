package launch.servlets.general.commands;

import launch.servlets.admin.commands.generic.includers.IncludeListToRequest;
import models.Exposition;
import models.ModelNameManager;
import models.Ticket;
import models.commands.FindTicketsByExposition;
import orm.ConnectionManager;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SearchExpositionById extends ServletCommand {
    private FindTicketsByExposition ticketsFinder;
    private IncludeListToRequest<Ticket> ticketIncluder = new IncludeListToRequest<>(
            this.servlet, ModelNameManager.INSTANCE.pluralName(Ticket.class)
    );

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exposition exposition = RepositoryManager.INSTANCE.get(Exposition.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("exposition", exposition);

        if (exposition != null) {
            ticketIncluder.withList(ticketsFinder.withExposition(exposition).execute())
                    .accept(request, response);
        }

        dispatcher("/jsp/general/observe-exposition.jsp").forward(request, response);
    }

    public SearchExpositionById(HttpServlet servlet) {
        super(servlet);
        try {
            ticketsFinder = new FindTicketsByExposition(Ticket.class, ConnectionManager.INSTANCE.get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
