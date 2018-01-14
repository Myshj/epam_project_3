package launch.servlets.services.admin.commands.generic;

import orm.Model;
import orm.repository.Repository;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowUpdateFormCommand<T extends Model> extends ModelCommand<T> {
    private ModelMetaInfo meta;

    public ShowUpdateFormCommand(
            Class<T> clazz,
            HttpServlet servlet,
            Repository<T> repository
    ) {
        super(servlet, repository);
        meta = MetaInfoManager.INSTANCE.get(clazz);
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            T entity = repository.getById(
                    Long.valueOf(request.getParameter("id"))
            ).orElse(null);
            request.setAttribute(
                    "entity",
                    entity
            );
            request.setAttribute("action", "update");
            request.setAttribute("meta", meta);
            dispatcher(
                    "/jsp/admin/edit-entity.jsp"

            ).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
