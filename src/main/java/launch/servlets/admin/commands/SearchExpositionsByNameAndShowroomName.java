package launch.servlets.admin.commands;

import launch.servlets.admin.commands.generic.ForwardingCommand;
import launch.servlets.admin.commands.generic.ShowList;
import models.Exposition;
import models.commands.FindExpositionsByNameAndShowroomName;
import orm.repository.Repository;
import utils.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchExpositionsByNameAndShowroomName extends ForwardingCommand<Exposition> {
    private FindExpositionsByNameAndShowroomName finder;

    public SearchExpositionsByNameAndShowroomName(
            Class<Exposition> clazz,
            HttpServlet servlet,
            Repository<Exposition> repository,
            ShowList<Exposition> showList
    ) {
        super(servlet, repository, showList);
        try {
            finder = new FindExpositionsByNameAndShowroomName(
                    clazz,
                    ConnectionManager.INSTANCE.get()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<Exposition> result = null;
        try {
            result = repository.filter(
                    finder.withName(
                            request.getParameter("name")
                    ).withShowroomName(
                            request.getParameter("showroomName")
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        showList.withList(result).execute(request, response);
    }
}
