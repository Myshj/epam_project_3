package launch.servlets;

import models.OrderState;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "OrderStateServlet",
        urlPatterns = {"/admin/order-state"}
)
public class OrderStateServlet extends ModelServlet<OrderState> {
    @Override
    protected Class<OrderState> clazz() {
        return OrderState.class;
    }
}
