package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "ticket_types")
public class TicketType extends Model {
    private StringField name = new StringField(false);

    public TicketType(
            String name
    ) {
        this.name.setValue(name);
    }

    public TicketType() {
    }

    public StringField getName() {
        return name;
    }
}
