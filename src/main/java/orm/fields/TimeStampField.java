package orm.fields;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class TimeStampField extends SimpleOrmField<Timestamp> {
    public TimeStampField(boolean nullable) {
        super(nullable);
    }

    public TimeStampField setValue(LocalDateTime dateTime) {
        value = Timestamp.valueOf(dateTime);
        return this;
    }

    public LocalDateTime asLocalDateTime() {
        return value.toLocalDateTime();
    }

    public LocalDate asLocalDate(){
        return value.toLocalDateTime().toLocalDate();
    }
}
