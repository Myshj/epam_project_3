package utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;
import orm.OrmFieldUtils;
import orm.fields.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.function.Function;

public class MapToEntityConverter<T extends Model> implements Function<Map<String, Object>, T> {
    private Map<String, Field> fieldMap;
    private Constructor<T> constructor;

    public MapToEntityConverter(Class<T> clazz) {
        fieldMap = OrmFieldUtils.getRelationalToObjectMapping(clazz);
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            // e.printStackTrace();
        }
    }

    @Override
    public T apply(Map<String, Object> map) {
        try {
            return writeFields(constructor.newInstance(), map);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private T writeFields(T entity, Map<String, Object> map) {
        for (Map.Entry<String, Field> pair : fieldMap.entrySet()) {
            Field f = pair.getValue();
            String s = pair.getKey();
            Class type = f.getType();
            try {
                Object realField = FieldUtils.readField(f, entity, true);
                Object value = map.get(s);
                if (type == IntegerField.class) {
                    ((IntegerField) realField).setValue(((Integer) value).longValue());

                } else if (type == StringField.class) {
                    ((StringField) realField).setValue((String) value);
                } else if (type == ForeignKey.class) {
                    FieldUtils.writeField(
                            realField, "id", ((Integer) value).longValue(),
                            true
                    );
                } else if (type == EnumField.class) {
                    ((EnumField) realField).setValue(
                            Enum.valueOf(
                                    (Class) FieldUtils.readField(realField, "clazz", true),
                                    (String) value
                            )
                    );
                } else if (type == TimeStampField.class) {
                    ((TimeStampField) realField).setValue((Timestamp) value);
                } else if (type == DecimalField.class) {
                    ((DecimalField) realField).setValue((BigDecimal) value);
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return entity;
    }
}
