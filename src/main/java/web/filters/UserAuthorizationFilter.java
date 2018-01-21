package web.filters;

import models.User;

import javax.servlet.annotation.WebFilter;

/**
 * Filters access to user realm.
 */
@WebFilter(
        filterName = "UserAuthorizationFilter",
        urlPatterns = {"/user/*"}
)
public class UserAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean unauthorized(User user) {
        return user == null;
    }

}
