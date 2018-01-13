package models;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "buildings")
@EntityNames(singular = "building", plural = "buildings")
public class Building extends WebModel {

    private StringField name = new StringField(false);

    @Column(name = "street_id")
    @Relatives(pluralName = "streets")
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
