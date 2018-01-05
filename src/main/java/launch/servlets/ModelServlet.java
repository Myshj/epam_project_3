package launch.servlets;

import launch.servlets.commands.SearchByName;
import launch.servlets.commands.generic.*;
import launch.servlets.commands.generic.includers.IncludeAll;
import models.ModelNameManager;
import models.RelationsManager;
import models.WebModel;
import orm.RepositoryManager;
import orm.repository.Repository;
import utils.ResourceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class ModelServlet<T extends WebModel> extends HttpServlet {
    static final String SEARCH_BY_NAME = action("searchByName");
    static final String SEARCH_BY_ID = action("searchById");
    static final String NEW = action("new");
    static final String CREATE_NEW = action("createNew");
    static final String EDIT = action("edit");
    static final String REMOVE = action("remove");

    protected final Repository<T> repository = RepositoryManager.INSTANCE.get(clazz());
    final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getActions = new HashMap<>();
    final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postActions = new HashMap<>();

    protected final ForwardList<T> forwardList = new ForwardList<>(this, repository, pluralName());


    protected abstract Class<T> clazz();

    private String singularName() {
        return ModelNameManager.INSTANCE.singularName(clazz());
    }

    private String pluralName() {
        return ModelNameManager.INSTANCE.pluralName(clazz());
    }

    String message(String action) {
        return ResourceManager.MESSAGES.get(singularName() + action);
    }

    static String action(String name) {
        return ResourceManager.ACTIONS.get(name);
    }

    @Override
    public void init() throws ServletException {
        super.init();

        getActions.put(
                SEARCH_BY_NAME,
                new SearchByName<>(clazz(), this, repository, forwardList)
        );//, this::onSearchByName);
        getActions.put(
                SEARCH_BY_ID,
                new SearchById<>(this, repository, singularName())
        );//, this::onSearchById);
        getActions.put(
                NEW,
                new NewEntity<>(this, repository, singularName())
        );//, this::onNewEntity);


        postActions.put(
                CREATE_NEW,
                new CreateCommand<>(clazz(), this, forwardList, message("CreatedSuccessfully"))
        );//, this::onCreateNew);
        postActions.put(
                EDIT,
                new UpdateCommand<>(clazz(), this, forwardList, message("UpdatedSuccessfully"))
        );//, this::onEdit);
        postActions.put(
                REMOVE,
                new RemoveById<>(this, repository, forwardList, message("RemovedSuccessfully"))
        );//, this::onRemove);

        rememberRelatives();
    }

    private void rememberRelatives() {
        RelationsManager.INSTANCE.get(clazz()).forEach(
                (k, v) -> addCommandBefore(
                        getActions,
                        Arrays.asList(SEARCH_BY_ID, NEW),
                        new IncludeAll<>(v, this, k)
                )
        );
    }

    void addCommandBefore(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            String actionName,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        if (!actions.containsKey(actionName)) {
            return;
        }
        actions.put(actionName, command.andThen(actions.get(actionName)));
    }

    void addCommandBefore(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            List<String> actionNames,
            BiConsumer<HttpServletRequest, HttpServletResponse> command
    ) {
        actionNames.forEach(a -> addCommandBefore(actions, a, command));
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

    private void callActionOrListAll(
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> actions,
            String action,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        actions.getOrDefault(
                action,
                (a, b) -> forwardList.withList(repository.getAll()).accept(request, response)
        ).accept(request, response);
    }
}
