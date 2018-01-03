package launch.servlets;

import launch.servlets.commands.generic.includers.IncludeAll;
import models.User;
import models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "UserServlet",
        urlPatterns = {"/user"}
)
public class UserServlet extends ModelServlet<User> {

    @Override
    public void init() throws ServletException {
        super.init();
        IncludeAll<UserRole> includeRoles = new IncludeAll<>(
                UserRole.class,
                this,
                "userRoles"
        );
        getActions.put(
                "searchById",
                includeRoles.andThen(getActions.get("searchById"))
        );
        getActions.put(
                "new",
                includeRoles.andThen(getActions.get("new"))
        );
    }

    @Override
    protected Class<User> clazz() {
        return User.class;
    }

    @Override
    protected String singularName() {
        return "user";
    }

    @Override
    protected String pluralName() {
        return "users";
    }
}
