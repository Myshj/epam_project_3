package launch.servlets.services.admin;

import launch.servlets.services.ServletService;
import launch.servlets.services.admin.commands.ShowMainAdminPage;
import utils.meta.MetaInfoManager;

import javax.servlet.http.HttpServlet;

public class AdminService extends ServletService {

    public AdminService(HttpServlet servlet) {
        super(servlet);
        MetaInfoManager.INSTANCE.classes().forEach(
                c -> registerCommand(
                        String.format(
                                "/admin/%s/.*",
                                MetaInfoManager.INSTANCE.get(c).getNames().getSingular()
                        ),
                        new ModelAdminService(servlet, c)
                )
        );
        registerCommand("/admin(/)*", new ShowMainAdminPage(servlet));
    }
}
