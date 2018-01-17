package utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;
import orm.OrmFieldUtils;
import orm.fields.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Writes given fields to the given entity and returns changed entity.
 *
 * @param <T>
 */
public class FieldFromMapWriter<T extends Model> implements BiFunction<T, Map<String, Object>, T> {
    private Class<T> clazz;

    public FieldFromMapWriter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T apply(T entity, Map<String, Object> map) {
        for (Map.Entry<String, Field> pair : OrmFieldUtils.getRelationalToObjectMapping(clazz).entrySet()) {
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
