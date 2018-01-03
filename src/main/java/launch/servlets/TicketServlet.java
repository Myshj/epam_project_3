package launch.servlets;

import launch.servlets.commands.includers.IncludeAll;
import models.Exposition;
import models.Ticket;
import models.TicketType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.BiConsumer;

@WebServlet(
        name = "TicketServlet",
        urlPatterns = {"/ticket"}
)
public class TicketServlet extends ModelServlet<Ticket> {

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
    public void init() throws ServletException {
        super.init();

        IncludeAll<Exposition> includeExpositions = new IncludeAll<>(Exposition.class, this, "expositions");
        IncludeAll<TicketType> includeTicketTypes = new IncludeAll<>(TicketType.class, this, "types");
        BiConsumer<HttpServletRequest, HttpServletResponse> withExpositionsAndTypes = includeExpositions.andThen(
                includeTicketTypes
        );

        getActions.put(
                "new",
                withExpositionsAndTypes.andThen(getActions.get("new"))
        );

        getActions.put(
                "searchById",
                withExpositionsAndTypes.andThen(getActions.get("searchById"))
        );
    }
}
