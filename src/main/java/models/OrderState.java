package models;

import models.annotations.EntityNames;
import orm.annotations.Entity;
import orm.fields.BooleanField;
import orm.fields.StringField;

/**
 * Order state.
 * Has name.
 */
@Entity(table = "order_states")
@EntityNames(singular = "orderState", plural = "orderStates")
public class OrderState extends WebModel {
    private StringField name = new StringField(false);
    private BooleanField cancellable = new BooleanField(false);

    public OrderState(
            String name,
            boolean cancellable
    ) {
        this.name.setValue(name);
        this.cancellable.setValue(cancellable);
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

    public BooleanField getCancellable() {
        return cancellable;
    }
}
