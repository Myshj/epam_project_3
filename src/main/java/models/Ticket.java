package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.BooleanField;
import orm.fields.DecimalField;
import orm.fields.ForeignKey;
import orm.fields.StringField;
import utils.meta.annotations.EntityNames;
import utils.meta.annotations.Relatives;

import java.math.BigDecimal;

/**
 * Ticket.
 * Has type, price and is related with exposition.
 */
@Entity(table = "tickets")
@EntityNames(singular = "ticket", plural = "tickets")
public class Ticket extends Model {

    @Column(name = "exposition_id")
    @Relatives(pluralName = "expositions")
    private ForeignKey<Exposition> exposition = new ForeignKey<>(Exposition.class, false);

    @Column(name = "type_id")
    @Relatives(pluralName = "ticketTypes")
    private ForeignKey<TicketType> type = new ForeignKey<>(TicketType.class, false);

    private DecimalField price = new DecimalField(false);
    private StringField currency = new StringField(false);

    private BooleanField available = new BooleanField(false);

    public Ticket(
            Exposition exposition,
            TicketType type,
            BigDecimal price,
            String currency,
            boolean available
    ) {
        this.exposition.setValue(exposition);
        this.type.setValue(type);
        this.price.setValue(price);
        this.currency.setValue(currency);
        this.available.setValue(available);
    }

    public Ticket() {
    }

    @Override
    public String getDisplayName() {
        return String.format("%s, %s", exposition.getValue().getDisplayName(), type.getValue().getDisplayName());
    }

    public ForeignKey<Exposition> getExposition() {
        return exposition;
    }

    public ForeignKey<TicketType> getType() {
        return type;
    }

    public DecimalField getPrice() {
        return price;
    }

    public StringField getCurrency() {
        return currency;
    }

    public BooleanField getAvailable() {
        return available;
    }
}
