package launch.servlets.commands;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServletCommand<T extends Model> {
    protected final HttpServlet servlet;
    protected final Repository<T> repository;

    public ServletCommand(HttpServlet servlet, Repository<T> repository) {
        this.servlet = servlet;
        this.repository = repository;
    }

    protected final RequestDispatcher dispatcher(String url) {
        return servlet.getServletContext().getRequestDispatcher(url);
    }

    public abstract void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException;
}
