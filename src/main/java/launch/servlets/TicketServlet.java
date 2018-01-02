package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import models.Exposition;
import models.Ticket;
import models.TicketType;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "TicketServlet",
        urlPatterns = {"/ticket"}
)
public class TicketServlet extends ModelServlet<Ticket> {

    private final IncludeListToRequest<Exposition> includeExpositions = new IncludeListToRequest<>(
            this,
            "expositions"
    );

    private final IncludeListToRequest<TicketType> includeTicketTypes = new IncludeListToRequest<>(
            this,
            "types"
    );

    @Override
    protected Class<Ticket> clazz() {
        return Ticket.class;
    }

    @Override
    protected String singularName() {
        return "ticket";
    }

    @Override
    protected String pluralName() {
        return "tickets";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The ticket removed successfully.";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The ticket created successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The ticket updated successfully.";
    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {

        try {
            includeExpositions.withList(RepositoryManager.INSTANCE.get(Exposition.class).getAll()).execute(req, resp);
            includeTicketTypes.withList(RepositoryManager.INSTANCE.get(TicketType.class).getAll()).execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        super.onNewEntity(req, resp);
    }

    @Override
    protected void onSearchById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeExpositions.withList(RepositoryManager.INSTANCE.get(Exposition.class).getAll()).execute(req, resp);
            includeTicketTypes.withList(RepositoryManager.INSTANCE.get(TicketType.class).getAll()).execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        super.onSearchById(req, resp);
    }
}
