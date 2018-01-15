package orm.fields;

/**
 * Stores Integer value.
 */
public final class IntegerField extends SimpleOrmField<Long> {
    public IntegerField(boolean nullable) {
        super(nullable);
    }
}
