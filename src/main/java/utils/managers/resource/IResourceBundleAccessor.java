package utils.managers.resource;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public interface IResourceBundleAccessor extends Supplier<ResourceBundle> {
    IResourceBundleAccessor withResource(String resource);

    IResourceBundleAccessor withLocale(Locale locale);
}
