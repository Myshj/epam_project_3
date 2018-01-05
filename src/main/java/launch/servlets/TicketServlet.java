package launch.servlets;

import launch.servlets.commands.generic.includers.IncludeAll;
import models.Exposition;
import models.Ticket;
import models.TicketType;
import orm.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.function.BiConsumer;

@WebServlet(
        name = "TicketServlet",
        urlPatterns = {"/admin/ticket"}
)
public class TicketServlet extends ModelServlet<Ticket> {

    @Override
    protected Class<Ticket> clazz() {
        return Ticket.class;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        IncludeAll<Exposition> includeExpositions = new IncludeAll<>(Exposition.class, this, Model.pluralName(Exposition.class));
        IncludeAll<TicketType> includeTicketTypes = new IncludeAll<>(TicketType.class, this, Model.pluralName(TicketType.class));
        BiConsumer<HttpServletRequest, HttpServletResponse> withExpositionsAndTypes = includeExpositions.andThen(
                includeTicketTypes
        );

        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), withExpositionsAndTypes);
    }
}
