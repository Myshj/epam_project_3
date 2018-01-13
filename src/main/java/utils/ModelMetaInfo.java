package utils;

import orm.Model;

import java.util.Map;

public class ModelMetaInfo {
    public final ModelNames names;
    public final Map<String, Class<? extends Model>> relatives;

    public ModelMetaInfo(
            ModelNames names,
            Map<String, Class<? extends Model>> relatives
    ) {
        this.names = names;
        this.relatives = relatives;
    }
}
