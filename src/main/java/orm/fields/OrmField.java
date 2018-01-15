package orm.fields;

import java.util.Optional;

/**
 * Base class for all entity fields.
 *
 * @param <T> What is stored in thi field.
 */
public abstract class OrmField<T> {


    private boolean nullable;

    /**
     * Constructor.
     * @param nullable Is it possible to store null references in this field.
     */
    public OrmField(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * Is it possible to store null references in this field.
     * @return
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * Returns Optional describing contained value.
     * @return
     */
    public abstract Optional<T> get();

    /**
     * Sets new value.
     * @param value Value to set from
     * @return this.
     */
    public abstract OrmField<T> setValue(T value);
}
