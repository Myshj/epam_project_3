package web.filters;

import models.User;

import javax.servlet.annotation.WebFilter;

@WebFilter(
        filterName = "AdminAuthorizationFilter",
        urlPatterns = {"/admin/*"}
)
public class AdminAuthorizationFilter extends AuthorizationFilter {

    @Override
    boolean unauthorized(User user) {
        return user == null || !user.getRole().getValue().getHasAccessToAdminSite().getValue();
    }
}
