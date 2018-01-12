package launch.servlets.services;

import launch.servlets.admin.commands.generic.*;
import launch.servlets.admin.commands.generic.includers.IncludeAll;
import models.RelationsManager;
import models.WebModel;
import orm.repository.Repository;
import utils.ModelNameManager;
import utils.RepositoryManager;
import utils.ResourceManager;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;

public class ModelAdminService<T extends WebModel> extends ServletService {

    private final Class<T> clazz;

    protected final Repository<T> repository;

    protected final ShowList<T> showList;

    public ModelAdminService(HttpServlet servlet, Class<T> clazz) {
        super(servlet);
        this.clazz = clazz;
        repository = RepositoryManager.INSTANCE.get(clazz);
        showList = new ShowList<>(this.servlet, repository, pluralName());
        init();
    }


    private String singularName() {
        return ModelNameManager.INSTANCE.singularName(clazz);
    }

    private String pluralName() {
        return ModelNameManager.INSTANCE.pluralName(clazz);
    }

    private String message(String action) {
        return ResourceManager.MESSAGES.get(singularName() + action);
    }

    private void init() {
        registerCommand(
                String.format("/admin/%s/show_all", singularName()),
                new ShowAllCommand<>(clazz, this.servlet, pluralName())
        );
        registerCommand(
                String.format("/admin/%s/show_update_form", singularName()),
                new ShowUpdateFormCommand<>(this.servlet, repository, singularName())
        );
        registerCommand(
                String.format("/admin/%s/show_create_form", singularName()),
                new ShowCreateFormCommand<>(this.servlet, repository, singularName())
        );
        registerCommand(
                String.format("/admin/%s/create", singularName()),
                new CreateEntityCommand<>(clazz, this.servlet, showList, message("CreatedSuccessfully"))
        );
        registerCommand(
                String.format("/admin/%s/update", singularName()),
                new UpdateEntityCommand<>(clazz, this.servlet, showList, message("UpdatedSuccessfully"))
        );
        registerCommand(
                String.format("/admin/%s/remove", singularName()),
                new RemoveEntityCommand<>(this.servlet, repository, showList, message("RemovedSuccessfully"))
        );

        rememberRelatives();
    }


    private void rememberRelatives() {
        RelationsManager.INSTANCE.get(clazz).forEach(
                (k, v) -> addCommandBefore(
                        Arrays.asList(
                                String.format("/admin/%s/show_update_form", singularName()),
                                String.format("/admin/%s/show_create_form", singularName())
                        ),
                        new IncludeAll<>(v, this.servlet, k)
                )
        );
    }
}
