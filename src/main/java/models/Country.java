package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "countries")
public class Country extends Model {
    private StringField name = new StringField(false);

    public Country(String name) {
        this.name.setValue(name);
    }

    public Country() {
    }

    public StringField getName() {
        return name;
    }
}