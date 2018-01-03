package models.entity_from_request_updaters;

import models.UserRole;

import javax.servlet.http.HttpServletRequest;

public class UserRoleUpdater extends EntityFromRequestUpdater<UserRole> {
    @Override
    public void accept(UserRole role, HttpServletRequest request) {
        role.getName().setValue(request.getParameter("name"));
    }
}
