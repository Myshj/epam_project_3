package launch.servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "UserServlet",
        urlPatterns = {"/admin/user"}
)
public class UserServlet extends ModelServlet<User> {

    @Override
    public void init() throws ServletException {
        super.init();
//        IncludeAll<UserRole> includeRoles = new IncludeAll<>(
//                UserRole.class,
//                this,
//                Model.pluralName(UserRole.class)
//        );
//        addCommandBefore(getActions, Arrays.asList(SEARCH_BY_ID, NEW), includeRoles);
    }

    @Override
    protected Class<User> clazz() {
        return User.class;
    }
}
