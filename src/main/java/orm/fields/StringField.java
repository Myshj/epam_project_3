package orm.fields;

/**
 * Stores string value.
 */
public final class StringField extends SimpleOrmField<String> {
    public StringField(boolean nullable) {
        super(nullable);
    }
}
