package launch.servlets;

import launch.servlets.commands.generic.includers.IncludeAll;
import models.Order;
import models.OrderState;
import models.Ticket;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.BiConsumer;

@WebServlet(
        name = "OrderServlet",
        urlPatterns = {"/order"}
)
public class OrderServlet extends ModelServlet<Order> {
    @Override
    protected Class<Order> clazz() {
        return Order.class;
    }

    @Override
    protected String singularName() {
        return "order";
    }

    @Override
    protected String pluralName() {
        return "orders";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        IncludeAll<Ticket> includeTickets = new IncludeAll<>(Ticket.class, this, "tickets");
        IncludeAll<User> includeUsers = new IncludeAll<>(User.class, this, "users");
        IncludeAll<OrderState> includeStates = new IncludeAll<>(OrderState.class, this, "orderStates");
        BiConsumer<HttpServletRequest, HttpServletResponse> includeRelatives = includeTickets.andThen(
                includeStates
        ).andThen(
                includeUsers
        );

        getActions.put(
                "new",
                includeRelatives.andThen(getActions.get("new"))
        );

        getActions.put(
                "searchById",
                includeRelatives.andThen(getActions.get("searchById"))
        );
    }
}
