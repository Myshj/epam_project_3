package utils.meta;

import orm.Model;

import java.util.Map;

/**
 * Stores metainfo about entity.
 */
public class ModelMetaInfo {
    private final ModelNames names;
    private final Map<String, Class<? extends Model>> relatives;
    private final Map<String, FieldMetaInfo> fields;

    /**
     * @return names of entity.
     */
    public ModelNames getNames() {
        return names;
    }

    /**
     *
     * @return map of name --> class referenced with ForeignKey of entity
     */
    public Map<String, Class<? extends Model>> getRelatives() {
        return relatives;
    }

    /**
     *
     * @return map of fieldName --> fieldMetaInfo of entity
     */
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
