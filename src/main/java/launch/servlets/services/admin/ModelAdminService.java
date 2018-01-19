package launch.servlets.services.admin;

import launch.servlets.ServiceContext;
import launch.servlets.services.ServletService;
import launch.servlets.services.admin.commands.generic.*;
import models.WebModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.managers.resource.IResourceManager;
import utils.meta.MetaInfoManager;

/**
 * Admin CRUD service for a single entity class.
 *
 * @param <T> Type of entity to work with.
 */
public class ModelAdminService<T extends WebModel> extends ServletService {
    private static final Logger logger = LogManager.getLogger(ModelAdminService.class);

    private final Class<T> clazz;

    private final IResourceManager messageManager;

    protected final ShowList<T> showList;

    public ModelAdminService(
            ServiceContext context,
            Class<T> clazz
    ) {
        super(context);
        logger.info("started construction");
        this.clazz = clazz;
        this.messageManager = context.getManagers().getResources().getMessages();
        showList = new ShowList<>(context, clazz);
        init();
        logger.info("constructed");
    }

    /**
     * Shortcut to retrieving singular name of entity .
     *
     * @return singular name of served entity.
     */
    private String singularName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getSingular();
    }

    /**
     * Shortcut to retrieving plural name of entity.
     *
     * @return plural name of served entity.
     */
    private String pluralName() {
        return MetaInfoManager.INSTANCE.get(clazz).getNames().getPlural();
    }

    /**
     * Returns localized name of action.
     *
     * @param action action to localize
     * @return localized name of action.
     */
    private String message(String action) {
        return messageManager.withKey(singularName() + action).get(); //ResourceManager.MESSAGES.get(singularName() + action);
    }

    /**
     * Registers child commands.
     */
    private void init() {
        logger.info("started child commands registration");
        registerCommand(
                String.format("/admin/%s/show_all", singularName()),
                new ShowAllCommand<>(context, clazz)
        );
        registerCommand(
                String.format("/admin/%s/show_update_form", singularName()),
                new ShowUpdateFormCommand<>(context, clazz)
        );
        registerCommand(
                String.format("/admin/%s/show_create_form", singularName()),
                new ShowCreateFormCommand<>(context, clazz)
        );
        registerCommand(
                String.format("/admin/%s/create", singularName()),
                new CreateEntityCommand<>(context, clazz, showList, message("CreatedSuccessfully"))
        );
        registerCommand(
                String.format("/admin/%s/update", singularName()),
                new UpdateEntityCommand<>(context, clazz, showList, message("UpdatedSuccessfully"))
        );
        registerCommand(
                String.format("/admin/%s/remove", singularName()),
                new RemoveEntityCommand<>(context, clazz, showList, message("RemovedSuccessfully"))
        );

        logger.info("registered child commands");
    }
}
