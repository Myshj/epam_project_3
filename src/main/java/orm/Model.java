package orm;

import orm.fields.IntegerField;

public abstract class Model {
    private IntegerField id = new IntegerField(false);

    public Model() {
    }

    public IntegerField getId() {
        return id;
    }

    public abstract String getDisplayName();
}
