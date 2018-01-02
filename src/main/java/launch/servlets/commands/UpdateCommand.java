package launch.servlets.commands;

import launch.servlets.managers.EntityFromRequestUpdaterManager;
import orm.Model;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public class UpdateCommand<T extends Model> extends ForwardingCommand<T> {
    Constructor<T> constructor;
    String updatedSuccessfullyMessage;
    BiConsumer<T, HttpServletRequest> updater;

    public UpdateCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ForwardList<T> forwardList,
            String updatedSuccessfullyMessage
    ) {
        super(servlet, RepositoryManager.INSTANCE.get(clazz), forwardList);
        this.updatedSuccessfullyMessage = updatedSuccessfullyMessage;
        updater = EntityFromRequestUpdaterManager.INSTANCE.get(clazz);
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T entity = null;
        try {
            entity = repository.getById(
                    Long.valueOf(
                            request.getParameter("id")
                    )
            ).orElse(
                    constructor.newInstance()
            );
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        updater.accept(entity, request);
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
