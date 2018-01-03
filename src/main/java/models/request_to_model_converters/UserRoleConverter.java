package models.request_to_model_converters;

import models.UserRole;

import javax.servlet.http.HttpServletRequest;

public class UserRoleConverter extends RequestToModelConverter<UserRole> {
    @Override
    public UserRole apply(HttpServletRequest request) {
        return new UserRole(
                request.getParameter("name")
        );
    }
}
