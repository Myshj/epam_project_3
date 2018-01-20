package services.user.commands;

import models.Order;
import models.User;
import models.queries.FindOrdersByUser;
import orm.queries.SqlQueryContext;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowOrdersOfUser extends ServletCommand {
    private final FindOrdersByUser orderFinder = new FindOrdersByUser(
            new SqlQueryContext<>(
                    Order.class,
                    context.getManagers().getRepository(),
                    context.getManagers().getConnection().get()
            )
    );

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(url("loginJsp"));
            return;
        }

        request.setAttribute("orders", orderFinder.withUser(user).get());

        dispatcher(url("observeUserOrdersJsp")).forward(request, response);
    }

    public ShowOrdersOfUser(ServletServiceContext context) {
        super(context);
    }
}
