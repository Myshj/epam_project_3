package launch.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(CharacterEncodingFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        // NOOP.
        logger.info("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("begin filter");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        logger.info("end filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("destroy");
        // NOOP.
    }
}
