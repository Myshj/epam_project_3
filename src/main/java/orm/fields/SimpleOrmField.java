package orm.fields;

import java.util.Optional;

/**
 * Simplest possible implementation of OrmField.
 *
 * @param <T> What is stored in this field.
 */
public abstract class SimpleOrmField<T> extends OrmField<T> {
    protected T value;

    public SimpleOrmField(boolean nullable) {
        super(nullable);
    }

    @Override
    public Optional<T> get() {
        return Optional.ofNullable(value);
    }

    /**
     * Returns value of this field.
     * @return value of this field.
     */
    public T getValue() {
        return value;
    }

    /**
     * Checks nullability of the given value and possibly sets new value.
     * Throws NullPointerException if nullability check failes.
     * @param value Value to set from
     * @return this.
     */
    @Override
    public final SimpleOrmField<T> setValue(T value) {
        checkNullableConstraint(value);
        setCleaned(value);
        return this;
    }

    /**
     * Sets new value.
     * @param value value to set from.
     */
    protected void setCleaned(T value) {
        this.value = value;
    }

    /**
     * Throws NullPointerException if nullable==False and value==null.
     * @param value
     */
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
