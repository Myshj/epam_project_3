package models;

import models.annotations.EntityNames;
import models.annotations.Relatives;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "cities")
@EntityNames(singular = "city", plural = "cities")
public class City extends WebModel {

    private StringField name = new StringField(false);

    @Column(name = "country_id")
    @Relatives(pluralName = "countries")
    private ForeignKey<Country> country = new ForeignKey<>(Country.class, true);

    public City() {
    }

    public City(String name, Country country) {
        this.name.setValue(name);
        this.country.setValue(country);
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Country> getCountry() {

        return country;
    }
}
