package launch.filters;

import models.UserRole;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(
//        filterName = "AuthorizationFilter",
//        urlPatterns = {"/admin/*"}
//)
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserRole role = (UserRole) request.getSession().getAttribute("userRole");
        System.out.println(role);

        String requestURI = request.getRequestURI();

        if (role == null || !role.getName().getValue().equalsIgnoreCase("админ")) {
            response.sendRedirect("/jsp/login.jsp");
            return;
        }

        System.out.println(requestURI);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
