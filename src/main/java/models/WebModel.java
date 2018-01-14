package models;

import orm.Model;
import utils.meta.MetaInfoManager;
import utils.meta.ModelMetaInfo;

public abstract class WebModel extends Model {
    public ModelMetaInfo meta() {
        return MetaInfoManager.INSTANCE.get(this.getClass());
    }
}
