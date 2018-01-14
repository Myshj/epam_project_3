package launch.servlets.services.admin.commands.generic;

import launch.servlets.services.admin.commands.generic.includers.IncludeListToRequest;
import orm.Model;
import orm.repository.Repository;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowList<T extends Model> extends ModelCommand<T> {
    private IncludeListToRequest<T> includer;
    private ModelMetaInfo meta;

    public ShowList(
            Class<T> clazz,
            HttpServlet servlet,
            Repository<T> repository
    ) {
        super(servlet, repository);
        this.includer = new IncludeListToRequest<>(servlet, "entities");
        meta = MetaInfoManager.INSTANCE.get(clazz);
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
        request.setAttribute("meta", meta);
        dispatcher(
                "/jsp/admin/list-entities.jsp"

        ).forward(request, response);
    }
}
