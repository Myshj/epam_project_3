package services.user.commands;

import models.Order;
import models.OrderState;
import models.Ticket;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Purchases ticket for user if purchaseable.
 */
public class PurchaseTicket extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(PurchaseTicket.class);
    private final OrderState defaultNewState = context.getManagers()
            .getRepository()
            .get(OrderState.class)
            .getAll()
            .stream()
            .filter(state -> state.getName().getValue().equalsIgnoreCase("новый"))
            .findFirst().orElse(null);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Ticket ticket = context.getManagers().getRepository().get(Ticket.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);

        if (user == null) {
            logger.error("user is null --> redirect to login page");
            response.sendRedirect(url("loginPageJsp"));
            return;
        }

        if (ticket == null) {
            logger.error("wrong ticket --> doing nothing");
            response.sendRedirect(url("error500Jsp"));
            return;
        }

        context.getManagers().getRepository().get(Order.class).save(
                new Order(
                        LocalDateTime.now(),
                        ticket,
                        user,
                        defaultNewState
                )
        );

        response.sendRedirect(url("showUserOrders"));
    }

    public PurchaseTicket(ServletServiceContext context) {
        super(context);
    }
}
