package launch.servlets;

import models.OrderState;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "OrderStateServlet",
        urlPatterns = {"/order-state"}
)
public class OrderStateServlet extends ModelServlet<OrderState> {
    @Override
    protected Class<OrderState> clazz() {
        return OrderState.class;
    }

    @Override
    protected String singularName() {
        return "orderState";
    }

    @Override
    protected String pluralName() {
        return "orderStates";
    }
}
