package utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages internationalized strings of different purposes.
 */
public enum ResourceManager {
    APPLICATION("application"),
    ACTIONS("actions"),
    URLS("urls"),
    MESSAGES("messages");

    private String resourceName;
    private ResourceBundle rb;


    ResourceManager(String resourceName) {
        this.resourceName = resourceName;
        rb = ResourceBundle.getBundle(resourceName);
    }

    public void setLocale(Locale locale) {
        rb = ResourceBundle.getBundle(resourceName, locale);
    }

    public String get(String key) {
        return rb.getString(key);
    }
}
