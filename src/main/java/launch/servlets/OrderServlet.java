package launch.servlets;

import models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "OrderServlet",
        urlPatterns = {"/admin/order"}
)
public class OrderServlet extends ModelServlet<Order> {
    @Override
    protected Class<Order> clazz() {
        return Order.class;
    }

    @Override
    public void init() throws ServletException {
        super.init();
//        IncludeAll<Ticket> includeTickets = new IncludeAll<>(Ticket.class, this, Model.pluralName(Ticket.class));
//        IncludeAll<User> includeUsers = new IncludeAll<>(User.class, this, Model.pluralName(User.class));
//        IncludeAll<OrderState> includeStates = new IncludeAll<>(OrderState.class, this,
//                Model.pluralName(OrderState.class)
//        );
//        BiConsumer<HttpServletRequest, HttpServletResponse> includeRelatives = includeTickets.andThen(
//                includeStates
//        ).andThen(
//                includeUsers
//        );
//        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeRelatives);
    }
}
