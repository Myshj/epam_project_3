package launch.servlets.commands.generic;

import launch.servlets.managers.RequestToModelConverterManager;
import orm.Model;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.function.Function;

public class CreateCommand<T extends Model> extends ForwardingCommand<T> {
    private String createdSuccessfullyMessage;
    private Function<HttpServletRequest, T> converter;

    public CreateCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ForwardList<T> forwardList,
            String createdSuccessfullyMessage
    ) {
        super(
                servlet,
                RepositoryManager.INSTANCE.get(clazz),
                forwardList
        );
        this.createdSuccessfullyMessage = createdSuccessfullyMessage;
        this.converter = RequestToModelConverterManager.INSTANCE.get(clazz);
    }

    @Override
    public final void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T newEntity = null;
        try {
            newEntity = converter.apply(request);
            repository.save(newEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("id", newEntity.getId().get().orElse(null));
        request.setAttribute("message", createdSuccessfullyMessage);
        forwardList.withList(repository.getAll()).execute(request, response);
    }
}
