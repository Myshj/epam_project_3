package launch.servlets;

import models.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

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

//        IncludeAll<Exposition> includeExpositions = new IncludeAll<>(Exposition.class, this, Model.pluralName(Exposition.class));
//        IncludeAll<TicketType> includeTicketTypes = new IncludeAll<>(TicketType.class, this, Model.pluralName(TicketType.class));
//        BiConsumer<HttpServletRequest, HttpServletResponse> withExpositionsAndTypes = includeExpositions.andThen(
//                includeTicketTypes
//        );
//
//        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), withExpositionsAndTypes);
    }
}
