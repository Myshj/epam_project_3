package models;

import orm.Model;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

/**
 * Base class for all web-based models.
 */
public abstract class WebModel extends Model {
    /**
     * Shortcut for retrieving of model metainfo.
     *
     * @return
     */
    public ModelMetaInfo meta() {
        return MetaInfoManager.INSTANCE.get(this.getClass());
    }
}
