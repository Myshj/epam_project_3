package models.request_to_model_converters;

import models.User;
import models.UserRole;
import orm.RepositoryManager;

import javax.servlet.http.HttpServletRequest;

public class UserConverter extends RequestToModelConverter<User> {
    @Override
    public User apply(HttpServletRequest request) {
        return new User(
                request.getParameter("email"),
                request.getParameter("password"),
                RepositoryManager.INSTANCE.get(UserRole.class).getById(
                        Long.valueOf(request.getParameter("role"))
                ).orElse(null)
        );
    }
}
