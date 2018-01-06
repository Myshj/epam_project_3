package launch.servlets.admin;

import models.User;

import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "UserServlet",
        urlPatterns = {"/admin/user"}
)
public class UserServlet extends ModelServlet<User> {

    @Override
    protected Class<User> clazz() {
        return User.class;
    }
}
