package models;

import models.annotations.EntityNames;
import models.annotations.Relatives;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.TimeStampField;

import java.time.LocalDateTime;

/**
 * Represents order.
 * User pays some amount of money and purchases ticket.
 * Order has also a state.
 */
@Entity(table = "orders")
@EntityNames(singular = "order", plural = "orders")
public class Order extends WebModel {
    private TimeStampField made = new TimeStampField(false);

    @Column(name = "ticket_id")
    @Relatives(pluralName = "tickets")
    private ForeignKey<Ticket> ticket = new ForeignKey<>(Ticket.class, false);

    @Column(name = "user_id")
    @Relatives(pluralName = "users")
    private ForeignKey<User> user = new ForeignKey<>(User.class, false);

    @Column(name = "state_id")
    @Relatives(pluralName = "orderStates")
    private ForeignKey<OrderState> state = new ForeignKey<>(OrderState.class, false);

    public Order() {
    }

    public Order(
            LocalDateTime made,
            Ticket ticket,
            User user,
            OrderState state
    ) {
        this.made.setValue(made);
        this.ticket.setValue(ticket);
        this.user.setValue(user);
        this.state.setValue(state);

    }

    @Override
    public String getDisplayName() {
        return made.asLocalDateTime().toString();
    }

    public TimeStampField getMade() {
        return made;
    }

    public ForeignKey<Ticket> getTicket() {
        return ticket;
    }

    public ForeignKey<User> getUser() {
        return user;
    }

    public ForeignKey<OrderState> getState() {
        return state;
    }
}
