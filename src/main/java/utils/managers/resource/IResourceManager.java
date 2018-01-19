package utils.managers.resource;

import java.util.function.Supplier;

public interface IResourceManager extends Supplier<String> {

    IResourceManager withKey(String key);
}
