package utils.managers.resource;

import java.util.function.Supplier;

/**
 * Base interface for all resource managers.
 */
public interface IResourceManager extends Supplier<String> {

    IResourceManager withKey(String key);
}
