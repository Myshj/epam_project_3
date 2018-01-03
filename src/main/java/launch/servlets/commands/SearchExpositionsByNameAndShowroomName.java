package launch.servlets.commands;

import launch.servlets.commands.generic.ForwardList;
import launch.servlets.commands.generic.ForwardingCommand;
import models.Exposition;
import models.commands.FindExpositionsByNameAndShowroomName;
import orm.ConnectionManager;
import orm.repository.Repository;

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
            ForwardList<Exposition> forwardList
    ) {
        super(servlet, repository, forwardList);
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
        forwardList.withList(result).execute(request, response);
    }
}
