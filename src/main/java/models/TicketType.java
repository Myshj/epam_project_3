package models;

import models.annotations.EntityNames;
import orm.annotations.Entity;
import orm.fields.StringField;

/**
 * Ticket type.
 * Has name.
 */
@Entity(table = "ticket_types")
@EntityNames(singular = "ticketType", plural = "ticketTypes")
public class TicketType extends WebModel {
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
