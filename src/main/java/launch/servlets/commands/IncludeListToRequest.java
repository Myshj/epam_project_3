package launch.servlets.commands;

import orm.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IncludeListToRequest<T extends Model> extends ServletCommand<T> {
    private String name;
    private List<T> list;

    public IncludeListToRequest(
            HttpServlet servlet,
            String name
    ) {
        super(servlet, null);
        this.name = name;
    }

    public IncludeListToRequest<T> withList(List<T> list) {
        this.list = list;
        return this;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
                name,
                list
        );
    }
}
