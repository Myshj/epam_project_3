package models;

import models.annotations.EntityNames;
import models.annotations.Relatives;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

/**
 * Represents building
 * Has name and reference to street.
 */
@Entity(table = "buildings")
@EntityNames(singular = "building", plural = "buildings")
public class Building extends WebModel {
    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    /**
     * Name/number of the building.
     */
    private StringField name = new StringField(false);

    /**
     * Street building placed at.
     */
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
