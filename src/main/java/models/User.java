package models;

import orm.Model;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.fields.ForeignKey;
import orm.fields.StringField;

@Entity(table = "users")
public class User extends Model {
    static {
        registerNames(User.class, "user", "users");
    }
    private StringField email = new StringField(false);
    private StringField password = new StringField(false);

    @Column(name = "role_id")
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
