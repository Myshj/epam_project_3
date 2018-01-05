package models;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.TimeStampField;

import java.time.LocalDateTime;

@Entity(table = "orders")
public class Order extends WebModel {
    private TimeStampField made = new TimeStampField(false);

    @Column(name = "ticket_id")
    private ForeignKey<Ticket> ticket = new ForeignKey<>(Ticket.class, false);

    @Column(name = "user_id")
    private ForeignKey<User> user = new ForeignKey<>(User.class, false);

    @Column(name = "state_id")
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
