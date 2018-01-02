package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "buildings")
public class Building extends Model {
    private StringField name = new StringField(false);

    @Column(name = "street_id")
    private ForeignKey<Street> street = new ForeignKey<>(Street.class, false);

    public Building(
            String name,
            Street street
    ) {
        this.name.setValue(name);
        this.street.setValue(street);
    }

    public Building() {
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Street> getStreet() {
        return street;
    }
}
