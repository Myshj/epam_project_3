package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "cities")
public class City extends Model {
    static {
        registerNames(City.class, "city", "cities");
    }

    private StringField name = new StringField(false);
    @Column(name = "country_id")
    private ForeignKey<Country> country = new ForeignKey<>(Country.class, true);

    public City() {
    }

    public City(String name, Country country) {
        this.name.setValue(name);
        this.country.setValue(country);
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Country> getCountry() {

        return country;
    }
}
