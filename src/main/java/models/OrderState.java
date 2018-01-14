package models;

import models.annotations.EntityNames;
import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "order_states")
@EntityNames(singular = "orderState", plural = "orderStates")
public class OrderState extends WebModel {
    private StringField name = new StringField(false);

    public OrderState(
            String name
    ) {
        this.name.setValue(name);
    }

    public OrderState() {
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }
}
