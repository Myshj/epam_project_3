package utils.managers.resource;

public class ResourceManager implements IResourceManager {
    private final IResourceBundleAccessor accessor;
    private String key;


    public ResourceManager(IResourceBundleAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public String get() {
        if (key == null || accessor == null) {
            return null;
        }
        return accessor.get().getString(key);
    }


    @Override
    public IResourceManager withKey(String key) {
        this.key = key;
        return this;
    }
}
