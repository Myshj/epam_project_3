package services.registration.commands;

import data_access.queries.FindUserByEmailAndPassword;
import models.User;
import models.UserRole;
import orm.repository.impl.sql.queries.SqlQueryContext;
import services.ServletServiceContext;
import services.commands.ServletCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUser extends ServletCommand {
    private final FindUserByEmailAndPassword userFinder = new FindUserByEmailAndPassword(
            new SqlQueryContext<>(
                    User.class,
                    context.getManagers().getRepository(),
                    context.getManagers().getConnection().get()
            )
    );

    private final UserRole defaultUserRole = context.getManagers().getRepository().get(UserRole.class).getAll().stream()
            .filter(role -> role.getName().getValue().equalsIgnoreCase("пользователь"))
            .findFirst().orElse(null);

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userFinder
                .withEmail(email)
                .withPassword(password)
                .get();

        if (user == null) {
            user = new User(email, password, defaultUserRole);
            context.getManagers().getRepository().get(User.class).save(user);
        }

        request.getSession().setAttribute("user", user);

        response.sendRedirect(url("loginConfirm"));
    }

    public RegisterUser(ServletServiceContext context) {
        super(context);
    }
}
