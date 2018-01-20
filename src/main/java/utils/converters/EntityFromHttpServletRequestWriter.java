package utils.converters;

import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;
import orm.fields.*;
import services.HasServiceContext;
import services.ServletServiceContext;
import utils.meta.functions.GetRelationalToObjectMapping;
import utils.meta.functions.GetUpdateMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Writes fields represented by http parameters into existing entity.
 */
public class EntityFromHttpServletRequestWriter<T extends Model> extends HasServiceContext implements BiFunction<T, HttpServletRequest, T> {

    private final Class<T> clazz;
    private final GetUpdateMapping getUpdateMapping = new GetUpdateMapping();
    private final GetRelationalToObjectMapping getRelationalToObjectMapping = new GetRelationalToObjectMapping();

    public EntityFromHttpServletRequestWriter(ServletServiceContext context, Class<T> clazz) {
        super(context);
        this.clazz = clazz;
    }

    @Override
    public T apply(T entity, HttpServletRequest request) {

        for (Map.Entry<String, Field> pair : getRelationalToObjectMapping.apply(clazz).entrySet()) {
            Field f = pair.getValue();
            if (!getUpdateMapping.apply(clazz).containsKey(f)) {
                continue;
            }
            String s = pair.getKey();
            Class type = f.getType();


            try {
                OrmField realField = (OrmField) FieldUtils.readField(f, entity, true);
                String value = request.getParameter(s);
                if (value == null && realField.isNullable()) {
                    continue;
                }
                if (type == IntegerField.class) {
                    ((IntegerField) realField).setValue(Long.valueOf(value));

                } else if (type == StringField.class) {
                    ((StringField) realField).setValue(value);
                } else if (type == ForeignKey.class) {
                    FieldUtils.writeField(
                            realField, "id", Long.valueOf(value),
                            true
                    );
                    FieldUtils.writeField(
                            realField, "repositoryManager", context.getManagers().getRepository(), true
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
                } else if (type == BooleanField.class) {
                    ((BooleanField) realField).setValue(Boolean.valueOf(value));
                }
            } catch (Exception e) {
                return null;
            }
        }
        return entity;
    }
}
