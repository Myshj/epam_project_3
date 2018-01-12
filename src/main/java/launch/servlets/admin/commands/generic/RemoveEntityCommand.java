package launch.servlets.admin.commands.generic;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private String message;

    public RemoveEntityCommand(
            HttpServlet servlet,
            Repository<T> repository,
            ShowList<T> showList,
            String message
    ) {
        super(servlet, repository, showList);
        this.message = message;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        repository.delete(
                repository.getById(
                        Long.valueOf(request.getParameter("id"))
                ).orElse(null)
        );
        request.setAttribute("message", message);
        showList.withList(repository.getAll()).execute(request, response);
    }
}
