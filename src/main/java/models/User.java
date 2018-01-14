package models;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "users")
@EntityNames(singular = "user", plural = "users")
public class User extends WebModel {
    private StringField email = new StringField(false);
    private StringField password = new StringField(false);

    @Column(name = "role_id")
    @Relatives(pluralName = "userRoles")
    private ForeignKey<UserRole> role = new ForeignKey<>(UserRole.class, false);

    public User(
            String email,
            String password,
            UserRole role
    ) {
        this.email.setValue(email);
        this.password.setValue(password);
        this.role.setValue(role);
    }

    public User() {
    }

    @Override
    public String getDisplayName() {
        return email.getValue();
    }

    public StringField getEmail() {
        return email;
    }

    public StringField getPassword() {
        return password;
    }

    public ForeignKey<UserRole> getRole() {
        return role;
    }
}
