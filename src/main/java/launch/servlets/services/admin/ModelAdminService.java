package launch.servlets.services.admin;

import launch.servlets.services.ServletService;
import launch.servlets.services.admin.commands.generic.*;
import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import models.WebModel;
import orm.repository.Repository;
import utils.RepositoryManager;
import utils.ResourceManager;
import utils.meta.MetaInfoManager;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;

public class ModelAdminService<T extends WebModel> extends ServletService {

    private final Class<T> clazz;

    protected final Repository<T> repository;

    protected final ShowList<T> showList;

    public ModelAdminService(HttpServlet servlet, Class<T> clazz) {
        super(servlet);
        System.out.println("service constructor");
        this.clazz = clazz;
        repository = RepositoryManager.INSTANCE.get(clazz);
        showList = new ShowList<>(clazz, this.servlet, repository);
        init();
    }


    private String singularName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getSingular();
    }

    private String pluralName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getPlural();
    }

    private String message(String action) {
        return ResourceManager.MESSAGES.get(singularName() + action);
    }

    private void init() {
        registerCommand(
                String.format("/admin/%s/show_all", singularName()),
                new ShowAllCommand<>(clazz, this.servlet)
        );
        registerCommand(
                String.format("/admin/%s/show_update_form", singularName()),
                new ShowUpdateFormCommand<>(clazz, this.servlet, repository)
        );
        registerCommand(
                String.format("/admin/%s/show_create_form", singularName()),
                new ShowCreateFormCommand<>(clazz, this.servlet, repository)
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
        MetaInfoManager.INSTANCE.get(clazz).getRelatives().forEach(
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
