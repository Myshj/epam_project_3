package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;
import utils.meta.annotations.EntityNames;

/**
 * Ticket type.
 * Has name.
 */
@Entity(table = "ticket_types")
@EntityNames(singular = "ticketType", plural = "ticketTypes")
public class TicketType extends Model {
    private StringField name = new StringField(false);

    public TicketType(
            String name
    ) {
        this.name.setValue(name);
    }

    public TicketType() {
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }
}
