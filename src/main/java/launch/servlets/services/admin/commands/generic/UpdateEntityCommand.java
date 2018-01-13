package launch.servlets.services.admin.commands.generic;

import orm.Model;
import utils.HttpServletRequestToEntityConverter;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.function.Function;

public class UpdateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    Constructor<T> constructor;
    String updatedSuccessfullyMessage;
    //BiConsumer<T, HttpServletRequest> updater;
    Function<HttpServletRequest, T> updater;

    public UpdateEntityCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ShowList<T> showList,
            String updatedSuccessfullyMessage
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz), showList);
        this.updatedSuccessfullyMessage = updatedSuccessfullyMessage;
        updater = new HttpServletRequestToEntityConverter<>(clazz);
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T entity = updater.apply(request);
        entity.getId().setValue(Long.valueOf(request.getParameter("id")));
        try {
            repository.save(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("id", entity.getId().getValue());
        request.setAttribute("message", updatedSuccessfullyMessage);
        showList.withList(repository.getAll()).execute(request, response);
    }
}
