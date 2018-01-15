package orm.fields;

import java.math.BigDecimal;

/**
 * Stores BigDecimal value.
 */
public final class DecimalField extends SimpleOrmField<BigDecimal> {
    public DecimalField(boolean nullable) {
        super(nullable);
    }
}
