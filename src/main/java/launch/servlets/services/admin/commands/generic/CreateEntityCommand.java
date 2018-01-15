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

public class CreateEntityCommand<T extends Model> extends ForwardingCommand<T> {
    private String createdSuccessfullyMessage;
    private Function<HttpServletRequest, T> converter;

    public CreateEntityCommand(
            Class<T> clazz,
            HttpServlet servlet,
            ShowList<T> showList,
            String createdSuccessfullyMessage
    ) {
        super(
                servlet,
                RepositoryManager.INSTANCE.get(clazz),
                showList
        );
        this.createdSuccessfullyMessage = createdSuccessfullyMessage;
        this.converter = new HttpServletRequestToEntityConverter<>(clazz);
    }

    @Override
    public final void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        T newEntity = converter.apply(request);
        repository.save(newEntity);
        request.setAttribute("id", newEntity.getId().get().orElse(null));
        request.setAttribute("message", createdSuccessfullyMessage);
        showList.withList(repository.getAll()).execute(request, response);
    }
}
