package orm.fields;

import java.util.Optional;

public abstract class OrmField<T> {
    private boolean nullable;

    public OrmField(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isNullable() {
        return nullable;
    }

    public abstract Optional<T> get();

    public abstract OrmField<T> setValue(T value);
}
