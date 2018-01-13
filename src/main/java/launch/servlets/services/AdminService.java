package launch.servlets.services;

import utils.ModelNameManager;

import javax.servlet.http.HttpServlet;

public class AdminService extends ServletService {

    public AdminService(HttpServlet servlet) {
        super(servlet);
        ModelNameManager.INSTANCE.classes().forEach(
                c -> registerCommand(
                        String.format(
                                "/admin/%s/.*",
                                ModelNameManager.INSTANCE.singularName(c)
                        ),
                        new ModelAdminService(servlet, c)
                )
        );
    }
}
