package models;

import models.annotations.EntityNames;
import orm.annotations.Entity;
import orm.fields.StringField;

/**
 * Represents country.
 * Has name.
 */
@Entity(table = "countries")
@EntityNames(singular = "country", plural = "countries")
public class Country extends WebModel {

    private StringField name = new StringField(false);

    public Country(String name) {
        this.name.setValue(name);
    }

    public Country() {
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }
}
