package launch.servlets.general.commands;

import launch.servlets.admin.commands.generic.includers.IncludeListToRequest;
import models.Exposition;
import models.ModelNameManager;
import models.Showroom;
import models.commands.FindActiveExpositionsByShowroom;
import orm.ConnectionManager;
import orm.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SearchShowroomById extends ServletCommand {
    private IncludeListToRequest<Exposition> expositionIncluder = new IncludeListToRequest<>(
            this.servlet,
            ModelNameManager.INSTANCE.pluralName(Exposition.class)
    );

    private FindActiveExpositionsByShowroom expositionFinder;

    public SearchShowroomById(HttpServlet servlet) {
        super(servlet);
        try {
            expositionFinder = new FindActiveExpositionsByShowroom(
                    Exposition.class,
                    ConnectionManager.INSTANCE.get()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Showroom showroom = RepositoryManager.INSTANCE.get(Showroom.class).getById(
                Long.valueOf(request.getParameter("id"))
        ).orElse(null);
        request.setAttribute("showroom", showroom);
        expositionIncluder.withList(
                expositionFinder.withShowroom(showroom)
                        .withDateTime(LocalDateTime.now())
                        .execute()
        ).accept(request, response);

        request.setAttribute(
                "address",
                String.format(
                        "%s, %s, %s, %s",
                        showroom.getBuilding().getValue().getStreet().getValue().getCity().getValue().getCountry().getValue().getName(),
                        showroom.getBuilding().getValue().getStreet().getValue().getCity().getValue().getName(),
                        showroom.getBuilding().getValue().getStreet().getValue().getName(),
                        showroom.getBuilding().getValue().getName()
                )
        );

        dispatcher("/jsp/general/observe-showroom.jsp").forward(request, response);
    }
}