package launch.servlets.general.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleForwarder extends ServletCommand {
    private String url;

    public SimpleForwarder withUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcher(url).forward(request, response);
    }

    public SimpleForwarder(HttpServlet servlet) {
        super(servlet);
    }
}
