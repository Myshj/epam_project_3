package utils.converters;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.fields.*;
import orm.queries.SqlQueryContext;
import utils.meta.functions.GetRelationalToObjectMapping;

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
public class EntityFromMapWriter<T extends Model> implements BiFunction<T, Map<String, Object>, T> {
    private static final Logger logger = LogManager.getLogger(EntityFromMapWriter.class);

    private final SqlQueryContext<T> context;
    private final GetRelationalToObjectMapping getRelationalToObjectMapping = new GetRelationalToObjectMapping();

    public EntityFromMapWriter(SqlQueryContext<T> context) {
        this.context = context;
    }

    @Override
    public T apply(T entity, Map<String, Object> map) {
        logger.info("started execution");
        for (Map.Entry<String, Field> pair : getRelationalToObjectMapping.apply(context.getClazz()).entrySet()) {
            Field f = pair.getValue();
            String s = pair.getKey();
            Class type = f.getType();
            try {
                OrmField realField = (OrmField) FieldUtils.readField(f, entity, true);

                Object value = map.getOrDefault(s, null);

                if (value == null && realField.isNullable()) {
                    continue;
                }

                if (type == IntegerField.class) {
                    ((IntegerField) realField).setValue(((Integer) value).longValue());

                } else if (type == StringField.class) {
                    ((StringField) realField).setValue((String) value);
                } else if (type == ForeignKey.class) {
                    FieldUtils.writeField(
                            realField, "id", ((Integer) value).longValue(),
                            true
                    );
                    FieldUtils.writeField(
                            realField, "repositoryManager", context.getRepositoryManager(), true
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
            } catch (Exception e) {
                logger.error("exception occured --> return null");
                logger.error(e);
                return null;
            }
        }
        logger.info("executed");
        return entity;
    }
}
