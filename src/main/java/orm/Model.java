package orm;

import orm.fields.IntegerField;

/**
 * Base class for all entities to store in databases.
 * All subclasses MUST define constructor without parameters as well as initialize their xxxFields in that
 * constructor or earlier AND have an Entity annotation.
 */
public abstract class Model {
    private IntegerField id = new IntegerField(false);

    /**
     * MUST be redeclared in subclasses.
     */
    public Model() {
    }

    public IntegerField getId() {
        return id;
    }

    /**
     * Short entity name to display.
     *
     * @return short name to display.
     */
    public abstract String getDisplayName();
}
