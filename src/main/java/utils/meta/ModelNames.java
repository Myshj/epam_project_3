package utils.meta;

public class ModelNames {
    private final String singular;
    private final String plural;

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }

    public ModelNames(String singular, String plural) {
        this.singular = singular;
        this.plural = plural;
    }
}
