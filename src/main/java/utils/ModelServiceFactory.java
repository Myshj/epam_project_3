package utils;

import launch.servlets.services.admin.ModelAdminService;
import utils.meta.MetaInfoManager;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public enum ModelServiceFactory {
    INSTANCE;
    private MetaInfoManager nameManager = MetaInfoManager.INSTANCE;

    public Map<String, ModelAdminService> create(HttpServlet servlet) {
        return new HashMap<String, ModelAdminService>() {{
            nameManager.classes().forEach(
                    c -> put(nameManager.get(c).getNames().getSingular(), new ModelAdminService(servlet, c))
            );
        }};
    }
}
