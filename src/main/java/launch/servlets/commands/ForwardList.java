package launch.servlets.commands;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ForwardList<T extends Model> extends ServletCommand<T> {
    private final String name;
    private IncludeListToRequest<T> includer;

    public ForwardList(
            HttpServlet servlet,
            Repository<T> repository,
            String name
    ) {
        super(servlet, repository);
        this.name = name;
        this.includer = new IncludeListToRequest<>(servlet, name);
    }

    public ForwardList withList(List<T> list) {
        includer.withList(list);
        return this;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        includer.execute(request, response);
        dispatcher(
                String.format(
                        "/jsp/list-%s.jsp",
                        name
                )

        ).forward(request, response);
    }
}
