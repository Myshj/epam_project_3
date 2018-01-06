package launch.servlets.admin.commands.generic;

import orm.Model;
import orm.repository.Repository;
import utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchById<T extends Model> extends ModelCommand<T> {
    private String name;

    public SearchById(
            HttpServlet servlet,
            Repository<T> repository,
            String name
    ) {
        super(servlet, repository);
        this.name = name;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            request.setAttribute(
                    name,
                    repository.getById(
                            Integer.valueOf(request.getParameter("id"))
                    ).orElse(null)
            );
            request.setAttribute("action", "edit");
            dispatcher(
                    String.format(
                            ResourceManager.URLS.get("newEntityTemplate"),
                            name
                    )

            ).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}