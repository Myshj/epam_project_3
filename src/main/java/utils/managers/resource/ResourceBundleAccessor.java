package utils.managers.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Accesses resource bundles.
 */
public class ResourceBundleAccessor implements IResourceBundleAccessor {

    private String resource;
    private Locale locale;

    @Override
    public IResourceBundleAccessor withResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public IResourceBundleAccessor withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    @Override
    public ResourceBundle get() {
        return resource == null ? null :
                locale == null ? ResourceBundle.getBundle(resource) :
                        ResourceBundle.getBundle(resource, locale);
    }
}
