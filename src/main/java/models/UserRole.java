package models;

import models.annotations.EntityNames;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.BooleanField;
import orm.fields.StringField;

/**
 * User role.
 * Has name.
 */
@Entity(table = "roles")
@EntityNames(singular = "userRole", plural = "userRoles")
public class UserRole extends WebModel {
    private StringField name = new StringField(false);

    @Column(name = "has_access_to_admin_site")
    private BooleanField hasAccessToAdminSite = new BooleanField(false);

    public UserRole(
            String name,
            boolean hasAccessToAdminSite
    ) {
        this.name.setValue(name);
        this.hasAccessToAdminSite.setValue(hasAccessToAdminSite);
    }

    public UserRole() {
    }

    @Override
    public String getDisplayName() {
        return name.getValue();
    }

    public StringField getName() {
        return name;
    }

    public BooleanField getHasAccessToAdminSite() {
        return hasAccessToAdminSite;
    }
}
