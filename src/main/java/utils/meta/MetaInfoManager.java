package utils.meta;

import models.*;
import orm.Model;
import utils.meta.functions.GetFields;
import utils.meta.functions.GetNames;
import utils.meta.functions.GetRelatives;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores metainfo about entities.
 * Used in admin-related classes.
 */
public enum MetaInfoManager {
    /**
     * Add entity class to this list in order to have admin pages for it.
     */
    INSTANCE(
            Country.class, City.class, Street.class, Building.class,
            Showroom.class, Exposition.class, Ticket.class, TicketType.class,
            User.class, UserRole.class, Order.class, OrderState.class
    );


    private final Map<Class<? extends Model>, ModelMetaInfo> infoMap = new HashMap<>();

    /**
     * @param clazz class of model
     * @return model metainfo.
     */
    public final ModelMetaInfo get(Class<? extends Model> clazz) {
        return infoMap.getOrDefault(clazz, null);
    }

    /**
     *
     * @return set of all managed classes.
     */
    public final Set<Class<? extends Model>> classes() {
        return infoMap.keySet();
    }

    @SafeVarargs
    MetaInfoManager(Class<? extends Model>... classes) {
        for (Class<? extends Model> clazz : classes) {
            infoMap.put(
                    clazz,
                    new ModelMetaInfo(
                            new GetNames().apply(clazz),
                            new GetRelatives().apply(clazz),
                            new GetFields().apply(clazz)
                    )
            );
        }
    }
}
