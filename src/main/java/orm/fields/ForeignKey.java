package orm.fields;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import utils.RepositoryManager;

import java.util.Optional;

/**
 * References another object in database.
 *
 * @param <T> Type of object to reference.
 */
public final class ForeignKey<T extends Model> extends SimpleOrmField<T> {
    private static final Logger logger = LogManager.getLogger(ForeignKey.class);

    private Long id;
    private Class<T> clazz;

    /**
     * Constructor.
     * @param clazz class of reference to store.
     * @param nullable Is it possible to store null references in this field.
     */
    public ForeignKey(Class<T> clazz, boolean nullable) {
        super(nullable);
        logger.info("started construction");
        this.clazz = clazz;
        logger.info("constructed");
//        if (value == null) {
//            this.id = null;
//        } else {
//            this.id = value.getId().get().orElse(null);
//        }
    }

    @Override
    protected void setCleaned(T value) {
        logger.info("started setting value");
        if (value == null) {
            logger.info("null received --> setting null");
            this.id = null;
            this.value = null;
        } else {
            logger.info("received value --> setting value");
            this.value = value;
            this.id = value.getId().get().orElse(null);
        }
        logger.info("set value");
    }

    /**
     * Tries to select referenced object from database if does not contain it already.
     * If object is present there will be no database call.
     * @return Optional describing referenced object.
     */
    @Override
    public Optional<T> get() {
        logger.info("started getting value");
        if (id == null) {
            logger.info("null id --> returning nothing");
            return Optional.empty();
        }

        logger.info("id not null --> updating value");
        updateValue();
        logger.info("get value");
        return Optional.ofNullable(value);
    }

    /**
     * Tries to select referenced object from database if does not contain it already.
     * If object is present there will be no database call.
     * @return referenced object.
     */
    @Override
    public T getValue() {
        logger.info("started getting value");
        if (id == null) {
            logger.info("null id --> returning nothing");
            return null;
        }
        logger.info("id not null --> updating value");
        updateValue();
        logger.info("get value");
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
        logger.info("started updating value");
        if (value == null) {
            value = RepositoryManager.INSTANCE.get(clazz).getById(id).orElse(null);
        }
        logger.info("updated value");
    }
}
