package launch.servlets.admin.commands.generic;

import launch.servlets.admin.commands.generic.includers.IncludeAll;
import orm.Model;
import orm.RepositoryManager;
import utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardAll<T extends Model> extends ModelCommand<T> {
    private IncludeAll<T> includeAll;
    private String name;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        includeAll.accept(request, response);
        dispatcher(
                String.format(
                        ResourceManager.URLS.get("listEntitiesTemplate"),
                        name
                )

        ).forward(request, response);
    }

    public ForwardAll(Class<T> clazz, HttpServlet servlet, String name) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz));
        this.includeAll = new IncludeAll<>(clazz, servlet, name);
        this.name = name;
    }
}