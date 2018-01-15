package orm.fields;

import orm.Model;
import utils.RepositoryManager;

import java.util.Optional;

/**
 * References another object in database.
 *
 * @param <T> Type of object to reference.
 */
public final class ForeignKey<T extends Model> extends SimpleOrmField<T> {

    private Long id;
    private Class<T> clazz;

    /**
     * Constructor.
     * @param clazz class of reference to store.
     * @param nullable Is it possible to store null references in this field.
     */
    public ForeignKey(Class<T> clazz, boolean nullable) {
        super(nullable);
        this.clazz = clazz;
        if (value == null) {
            this.id = null;
        } else {
            this.id = value.getId().get().orElse(null);
        }
    }

    @Override
    protected void setCleaned(T value) {
        if (value == null) {
            this.id = null;
            this.value = null;
        } else {
            this.value = value;
            this.id = value.getId().get().orElse(null);
        }
    }

    /**
     * Tries to select referenced object from database if does not contain it already.
     * If object is present there will be no database call.
     * @return Optional describing referenced object.
     */
    @Override
    public Optional<T> get() {
        if (id == null) {
            return Optional.empty();
        } else {
            updateValue();
        }
        return Optional.ofNullable(value);
    }

    /**
     * Tries to select referenced object from database if does not contain it already.
     * If object is present there will be no database call.
     * @return referenced object.
     */
    @Override
    public T getValue() {
        if (id == null) {
            return null;
        } else {
            updateValue();
        }
        return value;
    }

    @Override
    public String toString() {
        return "" + id; ///id == null ? null : getValue().getDisplayName();
    }

    /**
     * If the reference to object is null, updates it from the database.
     */
    private void updateValue() {
        if (value == null) {
            value = RepositoryManager.INSTANCE.get(clazz).getById(id).orElse(null);
        }
    }
}
