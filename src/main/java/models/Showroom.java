package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "showrooms")
public class Showroom extends Model {
    static {
        registerNames(Showroom.class, "showroom", "showrooms");
    }

    private StringField name = new StringField(false);

    @Column(name = "building_id")
    private ForeignKey<Building> building = new ForeignKey<>(Building.class, false);

    public Showroom() {
    }

    public Showroom(
            String name,
            Building building
    ) {
        this.name.setValue(name);
        this.building.setValue(building);
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Building> getBuilding() {
        return building;
    }
}
