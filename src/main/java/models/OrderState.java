package models;

import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "order_states")
public class OrderState extends WebModel {
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
