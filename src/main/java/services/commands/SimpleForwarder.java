package services.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.ServletServiceContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards request and response to a specified URL.
 */
public class SimpleForwarder extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(SimpleForwarder.class);

    private String url;

    public SimpleForwarder withUrl(String url) {
        logger.info("remembered url");
        this.url = url;
        return this;
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
        dispatcher(url).forward(request, response);
        logger.info("executed");
    }

    public SimpleForwarder(ServletServiceContext context) {
        super(context);
    }
}
