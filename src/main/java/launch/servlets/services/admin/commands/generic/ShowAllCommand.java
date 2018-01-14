package launch.servlets.services.admin.commands.generic;

import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import orm.Model;
import utils.RepositoryManager;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAllCommand<T extends Model> extends ModelCommand<T> {
    private IncludeAll<T> includeAll;
    private ModelMetaInfo meta;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        includeAll.accept(request, response);
        request.setAttribute("meta", meta);
        dispatcher(
                "/jsp/admin/list-entities.jsp"

        ).forward(request, response);
    }

    public ShowAllCommand(Class<T> clazz, HttpServlet servlet) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz));
        meta = MetaInfoManager.INSTANCE.get(clazz);
        includeAll = new IncludeAll<>(clazz, servlet, "entities");
    }
}
