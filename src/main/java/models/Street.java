package models;

import models.annotations.EntityNames;
import models.annotations.Relatives;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "streets")
@EntityNames(singular = "street", plural = "streets")
public class Street extends WebModel {

    private StringField name = new StringField(false);

    @Column(name = "city_id")
    @Relatives(pluralName = "cities")
    private ForeignKey<City> city = new ForeignKey<>(City.class, false);

    public Street(
            String name,
            City city
    ) {
        this.name.setValue(name);
        this.city.setValue(city);
    }

    public Street() {
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<City> getCity() {
        return city;
    }
}
