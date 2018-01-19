package launch.servlets.services.common.commands;

import launch.servlets.ServiceContext;
import launch.servlets.services.admin.commands.generic.includers.IncludeAll;
import launch.servlets.services.commands.ServletCommand;
import models.Exposition;
import models.Showroom;
import models.commands.ExpositionCountingByDateCommand;
import models.commands.GetCountOfActiveExpositions;
import models.commands.GetCountOfOldExpositions;
import models.commands.GetCountOfPlannedExpositions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.repository.Repository;
import utils.meta.MetaInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Command to show the main page of our system.
 */
public class ShowMainPage extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ShowMainPage.class);

    private Repository<Exposition> expositionRepository = context.getManagers().getRepository().get(Exposition.class);
    private ExpositionCountingByDateCommand getCountOfActiveExpositions;
    private ExpositionCountingByDateCommand getCountOfOldExpositions;
    private ExpositionCountingByDateCommand getCountOfPlannedExpositions;
    private IncludeAll<Showroom> showroomsIncluder;


    public ShowMainPage(ServiceContext context) {
        super(context);
        logger.info("started construction");
        showroomsIncluder = new IncludeAll<>(
                context,
                Showroom.class,
                MetaInfoManager.INSTANCE.get(Showroom.class).getNames().getPlural()
        );
        try {
            getCountOfActiveExpositions = new GetCountOfActiveExpositions(
                    Exposition.class,
                    context.getManagers().getConnection().get()
            );
            getCountOfOldExpositions = new GetCountOfOldExpositions(
                    Exposition.class,
                    context.getManagers().getConnection().get()
                    //ConnectionServiceProvider.INSTANCE.get()
            );
            getCountOfPlannedExpositions = new GetCountOfPlannedExpositions(
                    Exposition.class,
                    context.getManagers().getConnection().get()
                    //ConnectionServiceProvider.INSTANCE.get()
            );
        } catch (SQLException e) {
            logger.error(e);
        }
        logger.info("constructed");
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");
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
        logger.info("executed");
    }
}
