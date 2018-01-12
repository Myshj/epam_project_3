package orm.fields;

import orm.Model;
import utils.RepositoryManager;

import java.util.Optional;

public final class ForeignKey<T extends Model> extends SimpleOrmField<T> {

    private Long id;
    private Class<T> clazz;

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

    @Override
    public Optional<T> get() {
        if (id == null) {
            return Optional.empty();
        } else {
            updateValue();
        }
        return Optional.ofNullable(value);
    }

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
        return "" + id;
    }

    private void updateValue() {
        if (value == null) {
            value = RepositoryManager.INSTANCE.get(clazz).getById(id).orElse(null);
        }
    }
}
