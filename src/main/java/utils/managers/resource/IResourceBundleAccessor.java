package utils.managers.resource;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Supplier;

/**
 * Base interface for all resource bundle accessors.
 */
public interface IResourceBundleAccessor extends Supplier<ResourceBundle> {
    IResourceBundleAccessor withResource(String resource);

    IResourceBundleAccessor withLocale(Locale locale);
}
