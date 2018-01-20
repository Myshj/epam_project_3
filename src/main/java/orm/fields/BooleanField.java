package orm.fields;

public class BooleanField extends SimpleOrmField<Boolean> {
    public BooleanField(boolean nullable) {
        super(nullable);
    }

    @Override
    public String toString() {
        return "" + (value ? 1 : 0);
    }
}
