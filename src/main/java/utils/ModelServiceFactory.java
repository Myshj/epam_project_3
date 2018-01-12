package utils;

import launch.servlets.services.ModelAdminService;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public enum ModelServiceFactory {
    INSTANCE;
    private ModelNameManager nameManager = ModelNameManager.INSTANCE;

    public Map<String, ModelAdminService> create(HttpServlet servlet) {
        return new HashMap<String, ModelAdminService>() {{
            nameManager.classes().forEach(
                    c -> put(nameManager.singularName(c), new ModelAdminService(servlet, c))
            );
        }};
    }
}
