package launch.servlets.admin;

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
}
