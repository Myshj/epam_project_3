package launch.servlets.admin.commands.generic.includers;

import launch.servlets.admin.commands.generic.ModelCommand;
import orm.Model;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IncludeAll<T extends Model> extends ModelCommand<T> {
    private String name;

    public IncludeAll(
            Class<T> clazz,
            HttpServlet servlet,
            String name
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz));
        this.name = name;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
                name,
                repository.getAll()
        );
    }
}
