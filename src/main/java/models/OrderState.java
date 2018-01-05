package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "order_states")
public class OrderState extends Model {
    static {
        registerNames(OrderState.class, "orderState", "orderStates");
    }
    private StringField name = new StringField(false);

    public OrderState(
            String name
    ) {
        this.name.setValue(name);
    }

    public OrderState() {
    }

    public StringField getName() {
        return name;
    }
}
