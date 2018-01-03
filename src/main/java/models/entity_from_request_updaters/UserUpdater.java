package models.entity_from_request_updaters;

import models.User;
import models.UserRole;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class UserUpdater extends EntityFromRequestUpdater<User> {
    @Override
    public void accept(User user, HttpServletRequest request) {
        user.getEmail().setValue(request.getParameter("email"));
        user.getPassword().setValue(request.getParameter("password"));
        user.getRole().setValue(
                RepositoryManager.INSTANCE.get(UserRole.class).getById(
                        Long.valueOf(request.getParameter("role"))
                ).orElse(null)
        );
    }
}
