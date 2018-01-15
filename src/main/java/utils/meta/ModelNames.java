package utils.meta;

/**
 * Stores singular and plural names of entity.
 */
public class ModelNames {
    private final String singular;
    private final String plural;

    /**
     * @return singular name
     */
    public String getSingular() {
        return singular;
    }

    /**
     *
     * @return plural name
     */
    public String getPlural() {
        return plural;
    }

    public ModelNames(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }
}
