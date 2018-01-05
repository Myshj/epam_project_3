package orm;

import orm.fields.IntegerField;

import java.util.HashMap;
import java.util.Map;

public abstract class Model {
    private IntegerField id = new IntegerField(false);

    private static Map<Class<? extends Model>, String> singularNames = new HashMap<>();
    private static Map<Class<? extends Model>, String> pluralNames = new HashMap<>();

    public static String singularName(Class<? extends Model> clazz) {
        return singularNames.get(clazz);
    }

    public static String pluralName(Class<? extends Model> clazz) {
        return pluralNames.get(clazz);
    }

    public Model() {
    }

    public IntegerField getId() {
        return id;
    }

    protected static void registerNames(
            Class<? extends Model> clazz,
            String singular,
            String plural
    ) {
        singularNames.putIfAbsent(clazz, singular);
        pluralNames.putIfAbsent(clazz, plural);
    }
}
