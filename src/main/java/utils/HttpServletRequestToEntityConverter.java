package utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;
import orm.OrmFieldUtils;
import orm.fields.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

/**
 * Makes entity from http request parameters.
 *
 * @param <T>
 */
public class HttpServletRequestToEntityConverter<T extends Model> implements Function<HttpServletRequest, T> {

    private Map<String, Field> fieldMap;
    private Constructor<T> constructor;
    private Map<Field, Integer> updateMapping;

    public HttpServletRequestToEntityConverter(
            Class<T> clazz
    ) {
        fieldMap = OrmFieldUtils.getRelationalToObjectMapping(clazz);
        updateMapping = OrmFieldUtils.getUpdateMapping(clazz);
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            // e.printStackTrace();
        }
    }

    @Override
    public T apply(HttpServletRequest request) {
        try {
            return writeFields(constructor.newInstance(), request);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Writes fields represented by http parameters into existing entity.
     *
     * @param entity  entity to write fields to
     * @param request request to read parameters from
     * @return entity
     */
    private T writeFields(T entity, HttpServletRequest request) {
        for (Map.Entry<String, Field> pair : fieldMap.entrySet()) {
            Field f = pair.getValue();
            if (!updateMapping.containsKey(f)) {
                continue;
            }
            String s = pair.getKey();
            Class type = f.getType();
            try {
                Object realField = FieldUtils.readField(f, entity, true);
                String value = request.getParameter(s);
                if (type == IntegerField.class) {
                    ((IntegerField) realField).setValue(Long.valueOf(value));

                } else if (type == StringField.class) {
                    ((StringField) realField).setValue(value);
                } else if (type == ForeignKey.class) {
                    FieldUtils.writeField(
                            realField, "id", Long.valueOf(value),
                            true
                    );
                } else if (type == EnumField.class) {
                    ((EnumField) realField).setValue(
                            Enum.valueOf(
                                    (Class) FieldUtils.readField(realField, "clazz", true),
                                    value
                            )
                    );
                } else if (type == TimeStampField.class) {
                    ((TimeStampField) realField).setValue(LocalDate.parse(value).atStartOfDay());
                } else if (type == DecimalField.class) {
                    ((DecimalField) realField).setValue(BigDecimal.valueOf(Double.valueOf(value)));
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return entity;
    }
}
