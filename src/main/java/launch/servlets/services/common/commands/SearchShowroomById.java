package launch.servlets.services.common.commands;

import launch.servlets.services.admin.commands.generic.includers.IncludeAddress;
import launch.servlets.services.admin.commands.generic.includers.IncludeListToRequest;
import launch.servlets.services.commands.ServletCommand;
import models.Exposition;
import models.Showroom;
import models.commands.FindExpositionsByShowroom;
import utils.ConnectionManager;
import utils.ModelNameManager;
import utils.RepositoryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SearchShowroomById extends ServletCommand {
    private IncludeListToRequest<Exposition> expositionIncluder = new IncludeListToRequest<>(
            this.servlet,
            ModelNameManager.INSTANCE.pluralName(Exposition.class)
    );

    private IncludeAddress addressIncluder = new IncludeAddress(this.servlet, "address");

    private FindExpositionsByShowroom expositionFinder;

    public SearchShowroomById(HttpServlet servlet) {
        super(servlet);
        try {
            expositionFinder = new FindExpositionsByShowroom(
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

        if (showroom != null) {
            List<Exposition> expositions = expositionFinder.withShowroom(showroom).execute();
            expositionIncluder.withList(expositions).accept(request, response);

            addressIncluder.withBuilding(showroom.getBuilding().getValue())
                    .accept(request, response);
            LocalDateTime now = LocalDateTime.now();

            request.setAttribute(
                    "oldExpositions",
                    expositions.stream()
                            .filter(exposition -> exposition.getEnds().asLocalDateTime().isBefore(now))
                            .collect(Collectors.toList())
            );

            request.setAttribute(
                    "activeExpositions",
                    expositions.stream()
                            .filter(exposition -> exposition.getEnds().asLocalDateTime().isAfter(now) && exposition.getBegins().asLocalDateTime().isBefore(now))
                            .collect(Collectors.toList())
            );

            request.setAttribute(
                    "plannedExpositions",
                    expositions.stream()
                            .filter(exposition -> exposition.getBegins().asLocalDateTime().isAfter(now))
                            .collect(Collectors.toList())
            );
        }

        dispatcher("/jsp/general/observe-showroom.jsp").forward(request, response);
    }
}
