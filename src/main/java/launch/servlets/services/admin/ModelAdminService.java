package launch.servlets.services.admin;

import launch.servlets.services.ServletService;
import launch.servlets.services.admin.commands.generic.*;
import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import models.WebModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.Repository;
import utils.RepositoryManager;
import utils.ResourceManager;
import utils.meta.MetaInfoManager;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;

/**
 * Admin CRUD service for a single entity class.
 *
 * @param <T> Type of entity to work with.
 */
public class ModelAdminService<T extends WebModel> extends ServletService {
    private static final Logger logger = LogManager.getLogger(ModelAdminService.class);

    private final Class<T> clazz;

    protected final Repository<T> repository;

    protected final ShowList<T> showList;

    public ModelAdminService(HttpServlet servlet, Class<T> clazz) {
        super(servlet);
        logger.info("started construction");
        this.clazz = clazz;
        repository = RepositoryManager.INSTANCE.get(clazz);
        showList = new ShowList<>(clazz, this.servlet, repository);
        init();
        logger.info("constructed");
    }

    /**
     * Shortcut to retrieving singular name of entity .
     * @return singular name of served entity.
     */
    private String singularName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getSingular();
    }

    /**
     * Shortcut to retrieving plural name of entity.
     * @return plural name of served entity.
     */
    private String pluralName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getPlural();
    }

    /**
     * Returns localized name of action.
     * @param action action to localize
     * @return localized name of action.
     */
    private String message(String action) {
        return ResourceManager.MESSAGES.get(singularName() + action);
    }

    /**
     * Registers child commands.
     */
    private void init() {
        logger.info("started child commands registration");
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
        logger.info("registered child commands");
    }

    /**
     * TO DELETE
     * Remembers to include relatives to request served by ShowUpdateForm and ShowCreateForm
     * commands.
     */
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
