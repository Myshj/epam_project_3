package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "streets")
public class Street extends Model {
    static {
        registerNames(Street.class, "street", "streets");
    }
    private StringField name = new StringField(false);

    @Column(name = "city_id")
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

    public StringField getName() {
        return name;
    }

    public ForeignKey<City> getCity() {
        return city;
    }
}
