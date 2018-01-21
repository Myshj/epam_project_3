package utils.managers;

import orm.Model;
import utils.meta.ModelMetaInfo;
import utils.meta.functions.GetFields;
import utils.meta.functions.GetNames;
import utils.meta.functions.GetRelatives;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Manages metainfo about models.
 */
public class MetaInfoManager implements Function<Class<? extends Model>, ModelMetaInfo> {
    private final Map<Class<? extends Model>, ModelMetaInfo> infoMap = new HashMap<>();

    /**
     * @param clazz class of model
     * @return model metainfo.
     */
    public final ModelMetaInfo apply(Class<? extends Model> clazz) {
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
    public MetaInfoManager(Class<? extends Model>... classes) {
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
