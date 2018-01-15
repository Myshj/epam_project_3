package launch.servlets.services.admin.commands.generic;

import orm.Model;
import utils.HttpServletRequestToEntityConverter;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

public class UpdateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private String updatedSuccessfullyMessage;
    private Function<HttpServletRequest, T> updater;

    public UpdateEntityCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ShowList<T> showList,
            String updatedSuccessfullyMessage
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz), showList);
        this.updatedSuccessfullyMessage = updatedSuccessfullyMessage;
        updater = new HttpServletRequestToEntityConverter<>(clazz);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T entity = updater.apply(request);
        entity.getId().setValue(Long.valueOf(request.getParameter("id")));
        repository.save(entity);

        request.setAttribute("id", entity.getId().getValue());
        request.setAttribute("message", updatedSuccessfullyMessage);
        showList.withList(repository.getAll()).execute(request, response);
    }
}
