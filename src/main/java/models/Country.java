package models;

import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "countries")
@EntityNames(singular = "country", plural = "countries")
public class Country extends WebModel {

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
