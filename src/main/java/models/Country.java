package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;
import utils.meta.annotations.EntityNames;

/**
 * Represents country.
 * Has name.
 */
@Entity(table = "countries")
@EntityNames(singular = "country", plural = "countries")
public class Country extends Model {

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
