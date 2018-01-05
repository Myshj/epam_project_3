package launch.filters;

import models.UserRole;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserRole role = (UserRole) ((HttpServletRequest) servletRequest).getSession().getAttribute("userRole");
        System.out.println(role);

        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestURI.startsWith("/admin")) {
            if (role == null || !role.getName().getValue().equals("админ")) {
                ((HttpServletResponse) servletResponse).sendRedirect("/error");
                return;
            }
        }
        System.out.println(requestURI);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
