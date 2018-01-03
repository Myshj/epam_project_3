package launch.servlets.commands.generic;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiConsumer;

public abstract class ServletCommand<T extends Model> implements BiConsumer<HttpServletRequest, HttpServletResponse> {
    protected final HttpServlet servlet;
    protected Repository<T> repository;

    public ServletCommand(HttpServlet servlet, Repository<T> repository) {
        this.servlet = servlet;
        this.repository = repository;
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

    public abstract void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
