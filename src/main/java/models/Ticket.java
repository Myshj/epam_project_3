package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.DecimalField;
import orm.fields.ForeignKey;

import java.math.BigDecimal;

@Entity(table = "tickets")
public class Ticket extends Model {
    @Column(name = "exposition_id")
    private ForeignKey<Exposition> exposition = new ForeignKey<>(Exposition.class, false);

    @Column(name = "type_id")
    private ForeignKey<TicketType> type = new ForeignKey<>(TicketType.class, false);

    private DecimalField price = new DecimalField(false);

    public Ticket(
            Exposition exposition,
            TicketType type,
            BigDecimal price
    ) {
        this.exposition.setValue(exposition);
        this.type.setValue(type);
        this.price.setValue(price);
    }

    public Ticket() {
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
}