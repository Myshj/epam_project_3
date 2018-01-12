package launch.servlets.admin.commands.generic;

import launch.servlets.admin.commands.generic.includers.IncludeListToRequest;
import orm.Model;
import orm.repository.Repository;
import utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowList<T extends Model> extends ModelCommand<T> {
    private final String name;
    private IncludeListToRequest<T> includer;

    public ShowList(
            HttpServlet servlet,
            Repository<T> repository,
            String name
    ) {
        super(servlet, repository);
        this.name = name;
        this.includer = new IncludeListToRequest<>(servlet, name);
    }

    public ShowList withList(List<T> list) {
        includer.withList(list);
        return this;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        includer.execute(request, response);
        dispatcher(
                String.format(
                        ResourceManager.URLS.get("listEntitiesTemplate"),
                        name
                )

        ).forward(request, response);
    }
}
