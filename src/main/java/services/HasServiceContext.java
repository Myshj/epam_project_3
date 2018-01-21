package services;

import orm.Model;
import utils.meta.ModelMetaInfo;

public abstract class HasServiceContext {
    protected final ServletServiceContext context;

    protected String url(String key) {
        try {
            return context.getManagers().getResources().getUrls().withKey(key).get();
        } catch (Exception ex) {
            return key;
        }
    }

    protected ModelMetaInfo meta(Class<? extends Model> clazz) {
        return context.getManagers().getMetaInfo().apply(clazz);
    }

    public HasServiceContext(ServletServiceContext context) {
        this.context = context;
    }
}
