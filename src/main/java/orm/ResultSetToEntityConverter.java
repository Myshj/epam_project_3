package orm;

import org.apache.commons.lang3.reflect.FieldUtils;
import orm.fields.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ResultSetToEntityConverter<T extends Model> implements Function<ResultSet, Optional<T>> {
    private Map<String, Field> fieldMap;
    private Constructor<T> constructor;

    public ResultSetToEntityConverter(Class<T> clazz) {
        fieldMap = OrmFieldUtils.getRelationalToObjectMapping(clazz);
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            // e.printStackTrace();
        }
    }

    @Override
    public final Optional<T> apply(ResultSet resultSet) {
        try {
            return writeFields(constructor.newInstance(), resultSet);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            // e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<T> writeFields(T entity, ResultSet rs) {
        for (Map.Entry<String, Field> pair : fieldMap.entrySet()) {
            Field f = pair.getValue();
            String s = pair.getKey();
            Class type = f.getType();
            try {
                Object realField = FieldUtils.readField(f, entity, true);
                if (type == IntegerField.class) {
                    ((IntegerField) realField).setValue(rs.getLong(s));

                } else if (type == StringField.class) {
                    ((StringField) realField).setValue(rs.getString(s));
                } else if (type == ForeignKey.class) {
                    FieldUtils.writeField(
                            realField, "id", rs.getLong(s),
                            true
                    );
                } else if (type == EnumField.class) {
                    ((EnumField) realField).setValue(
                            Enum.valueOf(
                                    (Class) FieldUtils.readField(realField, "clazz", true),
                                    rs.getString(s)
                            )
                    );
                } else if (type == TimeStampField.class) {
                    ((TimeStampField) realField).setValue(rs.getTimestamp(s));
                } else if (type == DecimalField.class) {
                    ((DecimalField) realField).setValue(rs.getBigDecimal(s));
                }
            } catch (IllegalAccessException | SQLException e) {
                return Optional.empty();
            }
        }
        return Optional.ofNullable(entity);
    }
}
