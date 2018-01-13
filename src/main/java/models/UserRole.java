package models;

import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "roles")
@EntityNames(singular = "userRole", plural = "userRolea")
public class UserRole extends WebModel {
    private StringField name = new StringField(false);

    public UserRole(
            String name
    ) {
        this.name.setValue(name);
    }

    public UserRole() {
    }

    public StringField getName() {
        return name;
    }
}
