package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;
import utils.meta.annotations.EntityNames;
import utils.meta.annotations.Relatives;

/**
 * Showroom.
 * Has name and is placed in building.
 */
@Entity(table = "showrooms")
@EntityNames(singular = "showroom", plural = "showrooms")
public class Showroom extends Model {

    private StringField name = new StringField(false);

    @Column(name = "building_id")
    @Relatives(pluralName = "buildings")
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

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }

    public ForeignKey<Building> getBuilding() {
        return building;
    }
}
