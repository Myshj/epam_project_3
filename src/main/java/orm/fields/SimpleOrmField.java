package orm.fields;

import java.util.Optional;

public abstract class SimpleOrmField<T> extends OrmField<T> {
    protected T value;

    public SimpleOrmField(boolean nullable) {
        super(nullable);
    }

    @Override
    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

    public T getValue() {
        return value;
    }

    @Override
    public final SimpleOrmField<T> setValue(T value) {
        checkNullableConstraint(value);
        setCleaned(value);
        return this;
    }

    protected void setCleaned(T value) {
        this.value = value;
    }

    private void checkNullableConstraint(T value) {
        if (!isNullable() && value == null) {
            throw new NullPointerException("Tried to assign null to non-null field!");
        }
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
