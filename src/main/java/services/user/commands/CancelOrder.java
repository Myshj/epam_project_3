package services.user.commands;

import models.Order;
import models.OrderState;
import models.User;
import orm.repository.IRepository;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cancels user order if it exists and cancellable.
 */
public class CancelOrder extends ServletCommand {
    private final OrderState defaultCancelledState = context.getManagers()
            .getRepository()
            .get(OrderState.class)
            .getAll()
            .stream()
            .filter(state -> state.getName().getValue().equalsIgnoreCase("отменён"))
            .findFirst().orElse(null);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IRepository<Order> orderIRepository = context.getManagers().getRepository().get(Order.class);
        Order order = orderIRepository
                .getById(Long.valueOf(request.getParameter("id")))
                .orElse(null);

        User user = (User) request.getSession().getAttribute("user");

        Long userId = user.getId().getValue();
        Long orderUserId = order.getUser().getValue().getId().getValue();
        boolean isCancellable = order.getState().getValue().getCancellable().getValue();

        if (order == null || user == null || !userId.equals(orderUserId)) {
            response.sendRedirect(url("loginJsp"));
            return;
        }

        if (!isCancellable) {
            response.sendRedirect(url("showUserOrders"));
            return;
        }

        order.getState().setValue(defaultCancelledState);

        orderIRepository.save(order);

        response.sendRedirect(url("showUserOrders"));
    }

    public CancelOrder(ServletServiceContext context) {
        super(context);
    }
}
