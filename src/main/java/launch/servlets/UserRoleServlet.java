package launch.servlets;

import models.UserRole;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "UserRoleServlet",
        urlPatterns = {"/admin/user-role"}
)
public class UserRoleServlet extends ModelServlet<UserRole> {
    @Override
    protected Class<UserRole> clazz() {
        return UserRole.class;
    }

    @Override
    protected String singularName() {
        return "userRole";
    }

    @Override
    protected String pluralName() {
        return "userRoles";
    }
}
