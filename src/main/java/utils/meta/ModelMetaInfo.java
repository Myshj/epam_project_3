package utils.meta;

import orm.Model;

import java.util.Map;

public class ModelMetaInfo {
    private final ModelNames names;
    private final Map<String, Class<? extends Model>> relatives;
    private final Map<String, FieldMetaInfo> fields;

    public ModelNames getNames() {
        return names;
    }

    public Map<String, Class<? extends Model>> getRelatives() {
        return relatives;
    }

    public Map<String, FieldMetaInfo> getFields() {
        return fields;
    }

    public ModelMetaInfo(
            ModelNames names,
            Map<String, Class<? extends Model>> relatives,
            Map<String, FieldMetaInfo> fields
    ) {
        this.names = names;
        this.relatives = relatives;
        this.fields = fields;
    }
}
