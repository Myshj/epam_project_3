package services.common.commands;

import models.Exposition;
import models.Showroom;
import models.commands.ExpositionCountingByDateQuery;
import models.commands.GetCountOfActiveExpositions;
import models.commands.GetCountOfOldExpositions;
import models.commands.GetCountOfPlannedExpositions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.SqlQueryContext;
import orm.repository.IRepository;
import services.ServletServiceContext;
import services.admin.commands.generic.includers.IncludeAll;
import services.commands.ServletCommand;
import utils.meta.MetaInfoManager;
import utils.transactions.TransactionExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Command to show the main page of our system.
 */
public class ShowMainPage extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ShowMainPage.class);

    private IRepository<Exposition> expositionRepository = context.getManagers().getRepository().get(Exposition.class);
    private ExpositionCountingByDateQuery getCountOfActiveExpositions;
    private ExpositionCountingByDateQuery getCountOfOldExpositions;
    private ExpositionCountingByDateQuery getCountOfPlannedExpositions;
    private IncludeAll<Showroom> showroomsIncluder;


    public ShowMainPage(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        showroomsIncluder = new IncludeAll<>(
                context,
                Showroom.class,
                MetaInfoManager.INSTANCE.get(Showroom.class).getNames().getPlural()
        );
        getCountOfActiveExpositions = new GetCountOfActiveExpositions(
                new SqlQueryContext<>(
                        Exposition.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
        );
        getCountOfOldExpositions = new GetCountOfOldExpositions(
                new SqlQueryContext<>(
                        Exposition.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
                //ConnectionServiceProvider.INSTANCE.get()
        );
        getCountOfPlannedExpositions = new GetCountOfPlannedExpositions(
                new SqlQueryContext<>(
                        Exposition.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
                //ConnectionServiceProvider.INSTANCE.get()
        );
        logger.info("constructed");
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("started execution");

        new TransactionExecutor(context.getManagers().getConnection().get()).apply(
                () -> {
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
                }
        );

        dispatcher(url("mainPageJsp")).forward(request, response);
        logger.info("executed");
    }
}
