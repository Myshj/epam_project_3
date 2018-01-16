package launch.filters;

import models.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(
//        filterName = "AuthorizationFilter",
//        urlPatterns = {"/admin/*"}
//)
public class AuthorizationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("begin filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserRole role = (UserRole) request.getSession().getAttribute("userRole");
        System.out.println(role);

        if (unauthorized(role)) {
            logger.info("user unauthorized");
            response.sendRedirect("/jsp/login.jsp");
            logger.info("redirected to login service");
            return;
        }

        logger.info("end filter");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean unauthorized(UserRole role) {
        return role == null || !role.getName().getValue().equalsIgnoreCase("админ");
    }

    @Override
    public void destroy() {
        logger.info("destroy");
    }
}
