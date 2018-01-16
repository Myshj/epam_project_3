package launch.servlets.services.admin.commands;

import launch.servlets.services.commands.ServletCommand;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class ShowMainAdminPage extends ServletCommand {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(
                "metaInfos",
                MetaInfoManager.INSTANCE.classes().stream()
                        .map(MetaInfoManager.INSTANCE::get)
                        .collect(Collectors.toList())
        );
        dispatcher("/jsp/admin/main.jsp").forward(request, response);
    }

    public ShowMainAdminPage(HttpServlet servlet) {
        super(servlet);
    }
}
