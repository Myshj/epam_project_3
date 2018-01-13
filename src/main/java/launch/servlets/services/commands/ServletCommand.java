package launch.servlets.services.commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiConsumer;

public abstract class ServletCommand implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    protected final HttpServlet servlet;

    public ServletCommand(HttpServlet servlet) {
        this.servlet = servlet;
    }

    protected final RequestDispatcher dispatcher(String url) {
        return servlet.getServletContext().getRequestDispatcher(url);
    }

    @Override
    public final void accept(HttpServletRequest request, HttpServletResponse response) {
        try {
            execute(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
