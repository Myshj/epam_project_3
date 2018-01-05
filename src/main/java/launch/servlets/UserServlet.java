package launch.servlets;

import launch.servlets.commands.generic.includers.IncludeAll;
import models.User;
import models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Arrays;

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
        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeRoles);
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
