package launch.servlets;

import launch.servlets.commands.*;
import orm.Model;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ModelServlet<T extends Model> extends HttpServlet {
    protected final Repository<T> repository = RepositoryManager.INSTANCE.get(clazz());
    final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<>();
    final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postActions = new HashMap<>();

    protected final ForwardList<T> forwardList = new ForwardList<>(this, repository, pluralName());


    protected abstract Class<T> clazz();

    protected abstract String singularName();

    protected abstract String pluralName();

    protected abstract String removedSuccessfullyMessage();

    protected abstract String createdSuccessfullyMessage();

    protected abstract String updatedSuccessfullyMessage();

    @Override
    public void init() throws ServletException {
        super.init();

        getActions.put(
                "searchByName",
                new SearchByName<>(clazz(), this, repository, forwardList)
        );//, this::onSearchByName);
        getActions.put(
                "searchById",
                new SearchById<>(this, repository, singularName())
        );//, this::onSearchById);
        getActions.put(
                "new",
                new NewEntity<>(this, repository, singularName())
        );//, this::onNewEntity);

        postActions.put(
                "createNew",
                new CreateCommand<>(clazz(), this, forwardList, createdSuccessfullyMessage())
        );//, this::onCreateNew);
        postActions.put(
                "edit",
                new UpdateCommand<>(clazz(), this, forwardList, updatedSuccessfullyMessage())
        );//, this::onEdit);
        postActions.put(
                "remove",
                new RemoveById<>(this, repository, forwardList, removedSuccessfullyMessage())
        );//, this::onRemove);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        callActionOrListAll(getActions, req.getParameter("getAction"), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        callActionOrListAll(postActions, req.getParameter("postAction"), req, resp);
    }

    protected void callActionOrListAll(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            String action,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        actions.getOrDefault(
                action,
                (a, b) -> onForwardList(request, response, repository.getAll())
        ).accept(request, response);
    }

    protected void onForwardList(
            HttpServletRequest req,
            HttpServletResponse resp,
            List<T> list
    ) {
        try {
            forwardList.withList(list).execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
