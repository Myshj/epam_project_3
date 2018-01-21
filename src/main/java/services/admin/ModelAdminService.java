package services.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import services.ServletService;
import services.ServletServiceContext;
import services.admin.commands.generic.*;

/**
 * Admin CRUD service for a single entity class.
 *
 * @param <T> Type of entity to work with.
 */
public class ModelAdminService<T extends Model> extends ServletService {
    private static final Logger logger = LogManager.getLogger(ModelAdminService.class);

    private final Class<T> clazz;

    private final ShowList<T> showList;

    public ModelAdminService(
            ServletServiceContext context,
            Class<T> clazz
    ) {
        super(context);
        logger.info("started construction");
        this.clazz = clazz;
        showList = new ShowList<>(context, clazz);
        init();
        logger.info("constructed");
    }

    /**
     * Shortcut for retrieving of entity singular name.
     *
     * @return singular name of served entity.
     */
    private String singularName() {
        return meta(clazz).getNames().getSingular();
    }

    /**
     * Shortcut to retrieving plural name of entity.
     *
     * @return plural name of served entity.
     */
    private String pluralName() {
        return meta(clazz).getNames().getPlural();
    }

    /**
     * Registers child queries.
     */
    private void init() {
        logger.info("started child services registration");
        registerCommand(
                String.format(url("adminShowAll"), singularName()),
                new ShowAllCommand<>(context, clazz)
        );
        registerCommand(
                String.format(url("adminShowUpdateForm"), singularName()),
                new ShowUpdateFormCommand<>(context, clazz)
        );
        registerCommand(
                String.format(url("adminShowCreateForm"), singularName()),
                new ShowCreateFormCommand<>(context, clazz)
        );
        registerCommand(
                String.format(url("adminCreate"), singularName()),
                new CreateEntityCommand<>(context, clazz, showList)
        );
        registerCommand(
                String.format(url("adminUpdate"), singularName()),
                new UpdateEntityCommand<>(context, clazz, showList)
        );
        registerCommand(
                String.format(url("adminRemove"), singularName()),
                new RemoveEntityCommand<>(context, clazz, showList)
        );

        logger.info("registered child services");
    }
}
