package utils.converters;

import launch.servlets.ServiceContext;
import launch.servlets.services.HasServiceContext;
import org.apache.commons.lang3.reflect.FieldUtils;
import orm.Model;
import orm.OrmFieldUtils;
import orm.fields.*;

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

    public EntityFromHttpServletRequestWriter(ServiceContext context, Class<T> clazz) {
        super(context);
        this.clazz = clazz;
    }

    @Override
    public T apply(T entity, HttpServletRequest request) {
        for (Map.Entry<String, Field> pair : OrmFieldUtils.getRelationalToObjectMapping(clazz).entrySet()) {
            Field f = pair.getValue();
            if (!OrmFieldUtils.getUpdateMapping(clazz).containsKey(f)) {
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
                }
            } catch (Exception e) {
                return null;
            }
        }
        return entity;
    }
}
