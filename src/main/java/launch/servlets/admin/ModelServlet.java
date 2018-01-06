package launch.servlets.admin;

import launch.servlets.admin.commands.SearchByName;
import launch.servlets.admin.commands.generic.ForwardAll;
import launch.servlets.admin.commands.generic.includers.IncludeAll;
import launch.servlets.general.MyServlet;
import launch.servlets.general.commands.ServletCommand;
import models.ModelNameManager;
import models.RelationsManager;
import models.WebModel;
import orm.RepositoryManager;
import orm.repository.Repository;
import utils.ResourceManager;

import javax.servlet.ServletException;
import java.util.Arrays;

public abstract class ModelServlet<T extends WebModel> extends MyServlet {
    static final String SEARCH_BY_NAME = action("searchByName");
    static final String SEARCH_BY_ID = action("searchById");
    static final String NEW = action("new");
    static final String CREATE_NEW = action("createNew");
    static final String EDIT = action("edit");
    static final String REMOVE = action("remove");

    protected final Repository<T> repository = RepositoryManager.INSTANCE.get(clazz());

    protected final launch.servlets.admin.commands.generic.ForwardList<T> forwardList = new launch.servlets.admin.commands.generic.ForwardList<>(this, repository, pluralName());


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
                new launch.servlets.admin.commands.generic.SearchById<>(this, repository, singularName())
        );//, this::onSearchById);
        getActions.put(
                NEW,
                new launch.servlets.admin.commands.generic.NewEntity<>(this, repository, singularName())
        );//, this::onNewEntity);


        postActions.put(
                CREATE_NEW,
                new launch.servlets.admin.commands.generic.CreateCommand<>(clazz(), this, forwardList, message("CreatedSuccessfully"))
        );//, this::onCreateNew);
        postActions.put(
                EDIT,
                new launch.servlets.admin.commands.generic.UpdateCommand<>(clazz(), this, forwardList, message("UpdatedSuccessfully"))
        );//, this::onEdit);
        postActions.put(
                REMOVE,
                new launch.servlets.admin.commands.generic.RemoveById<>(this, repository, forwardList, message("RemovedSuccessfully"))
        );//, this::onRemove);

        rememberRelatives();
    }

    @Override
    protected ServletCommand defaultGetAction() {
        return new ForwardAll<>(clazz(), this, pluralName());
    }

    @Override
    protected ServletCommand defaultPostAction() {
        return new ForwardAll<>(clazz(), this, pluralName());
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
}
