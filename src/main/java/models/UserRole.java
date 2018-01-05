package models;

import orm.Model;
import orm.annotations.Entity;
import orm.fields.StringField;

@Entity(table = "roles")
public class UserRole extends Model {
    static {
        registerNames(UserRole.class, "userRole", "userRoles");
    }
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
