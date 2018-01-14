package utils.meta;

import orm.fields.*;

import java.util.HashMap;
import java.util.Map;

public class FieldMetaInfo {
    private final static Map<Class<? extends SimpleOrmField>, String> stringTypes =
            new HashMap<Class<? extends SimpleOrmField>, String>() {{
                put(StringField.class, "string");
                put(IntegerField.class, "integer");
                put(DecimalField.class, "decimal");
                put(ForeignKey.class, "foreign");
                put(TimeStampField.class, "date");
            }};
    private final String dbName;
    private final Class<? extends SimpleOrmField> referenceType;
    private final String stringType;
    private final String relatedName;

    public String getDbName() {
        return dbName;
    }

    public Class<? extends SimpleOrmField> getReferenceType() {
        return referenceType;
    }

    public String getStringType() {
        return stringType;
    }

    public String getRelatedName() {
        return relatedName;
    }

    public FieldMetaInfo(
            String dbName,
            Class<? extends SimpleOrmField> referenceType,
            String relatedName
    ) {
        this.dbName = dbName;
        this.referenceType = referenceType;
        this.relatedName = relatedName;
        stringType = stringTypes.getOrDefault(referenceType, null);
    }
}
