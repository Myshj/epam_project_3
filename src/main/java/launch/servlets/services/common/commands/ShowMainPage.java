package launch.servlets.services.common.commands;

import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import launch.servlets.services.commands.ServletCommand;
import models.Exposition;
import models.Showroom;
import models.commands.ExpositionCountingByDateCommand;
import models.commands.GetCountOfActiveExpositions;
import models.commands.GetCountOfOldExpositions;
import models.commands.GetCountOfPlannedExpositions;
import orm.repository.Repository;
import utils.ConnectionManager;
import utils.RepositoryManager;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ShowMainPage extends ServletCommand {
    private Repository<Exposition> expositionRepository = RepositoryManager.INSTANCE.get(Exposition.class);
    private ExpositionCountingByDateCommand getCountOfActiveExpositions;
    private ExpositionCountingByDateCommand getCountOfOldExpositions;
    private ExpositionCountingByDateCommand getCountOfPlannedExpositions;
    private IncludeAll<Showroom> showroomsIncluder = new IncludeAll<>(
            Showroom.class,
            this.servlet,
            MetaInfoManager.INSTANCE.get(Showroom.class).getNames().getPlural()
    );


    public ShowMainPage(HttpServlet servlet) {
        super(servlet);
        try {
            getCountOfActiveExpositions = new GetCountOfActiveExpositions(
                    Exposition.class,
                    ConnectionManager.INSTANCE.get()
            );
            getCountOfOldExpositions = new GetCountOfOldExpositions(
                    Exposition.class,
                    ConnectionManager.INSTANCE.get()
            );
            getCountOfPlannedExpositions = new GetCountOfPlannedExpositions(
                    Exposition.class,
                    ConnectionManager.INSTANCE.get()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showroomsIncluder.accept(request, response);

        LocalDateTime now = LocalDateTime.now();

        request.setAttribute(
                "countOfActiveExpositions",
                expositionRepository.count(getCountOfActiveExpositions.withDateTime(now))
        );

        request.setAttribute(
                "countOfOldExpositions",
                expositionRepository.count(getCountOfOldExpositions.withDateTime(now))
        );

        request.setAttribute(
                "countOfPlannedExpositions",
                expositionRepository.count(getCountOfPlannedExpositions.withDateTime(now))
        );

        dispatcher("/jsp/general/list-showrooms.jsp").forward(request, response);
    }
}
