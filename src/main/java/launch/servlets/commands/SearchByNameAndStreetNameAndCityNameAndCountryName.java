package launch.servlets.commands;

import launch.servlets.commands.generic.ForwardList;
import launch.servlets.commands.generic.ForwardingCommand;
import models.commands.FindEntitiesByNameAndStreetNameAndCityNameAndCountryName;
import orm.ConnectionManager;
import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchByNameAndStreetNameAndCityNameAndCountryName<T extends Model> extends ForwardingCommand<T> {
    private FindEntitiesByNameAndStreetNameAndCityNameAndCountryName<T> finder;

    public SearchByNameAndStreetNameAndCityNameAndCountryName(
            Class<T> clazz,
            HttpServlet servlet,
            Repository<T> repository,
            ForwardList<T> forwardList
    ) {
        super(servlet, repository, forwardList);
        try {
            finder = new FindEntitiesByNameAndStreetNameAndCityNameAndCountryName<>(
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
        List<T> result = null;
        try {
            result = repository.filter(
                    finder.withName(
                            request.getParameter("name")
                    ).withCountryName(
                            request.getParameter("countryName")
                    ).withCityName(
                            request.getParameter("cityName")
                    ).withStreetName(
                            request.getParameter("streetName")
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        forwardList.withList(result).execute(request, response);
    }
}
