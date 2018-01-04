package launch.servlets.commands.generic;

import orm.Model;
import orm.RepositoryManager;
import utils.HttpServletRequestToEntityConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.function.Function;

public class UpdateCommand<T extends Model> extends ForwardingCommand<T> {
    Constructor<T> constructor;
    String updatedSuccessfullyMessage;
    //BiConsumer<T, HttpServletRequest> updater;
    Function<HttpServletRequest, T> updater;

    public UpdateCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ForwardList<T> forwardList,
            String updatedSuccessfullyMessage
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz), forwardList);
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
        forwardList.withList(repository.getAll()).execute(request, response);
    }
}
