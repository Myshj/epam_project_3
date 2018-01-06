package launch.servlets.admin;

import models.Order;

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
}
