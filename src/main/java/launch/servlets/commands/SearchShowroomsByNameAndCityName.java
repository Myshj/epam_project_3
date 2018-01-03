package launch.servlets.commands;

import models.Showroom;
import models.commands.FindShowroomsByNameAndCityName;
import orm.ConnectionManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchShowroomsByNameAndCityName extends ForwardingCommand<Showroom> {
    private FindShowroomsByNameAndCityName finder;

    public SearchShowroomsByNameAndCityName(
            Class<Showroom> clazz,
            HttpServlet servlet,
            Repository<Showroom> repository,
            ForwardList<Showroom> forwardList
    ) {
        super(servlet, repository, forwardList);
        try {
            finder = new FindShowroomsByNameAndCityName(
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
        List<Showroom> result = null;
        try {
            result = repository.filter(
                    finder.withName(
                            request.getParameter("name")
                    ).withCityName(
                            request.getParameter("cityName")
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        forwardList.withList(result).execute(request, response);
    }
}