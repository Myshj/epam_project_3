package services.common.commands;

import models.Exposition;
import models.Showroom;
import models.queries.ExpositionCountingByDateAndShowroomQuery;
import models.queries.GetCountOfActiveExpositions;
import models.queries.GetCountOfOldExpositions;
import models.queries.GetCountOfPlannedExpositions;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command to show the main page of our system.
 */
public class ShowMainPage extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(ShowMainPage.class);

    private IRepository<Exposition> expositionRepository = context.getManagers().getRepository().get(Exposition.class);
    private ExpositionCountingByDateAndShowroomQuery getCountOfActiveExpositions;
    private ExpositionCountingByDateAndShowroomQuery getCountOfOldExpositions;
    private ExpositionCountingByDateAndShowroomQuery getCountOfPlannedExpositions;
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
                    Map<Showroom, Map<String, Long>> types = new HashMap<>();

                    for (Showroom showroom : (List<Showroom>) request.getAttribute("showrooms")) {
                        types.put(showroom, new HashMap<>());
                        types.get(showroom).put(
                                "active",
                                expositionRepository.count(
                                        getCountOfActiveExpositions
                                                .withDateTime(now)
                                                .withShowroom(showroom)
                                )
                        );
                        types.get(showroom).put(
                                "old",
                                expositionRepository.count(
                                        getCountOfOldExpositions
                                                .withDateTime(now)
                                                .withShowroom(showroom)
                                )
                        );
                        types.get(showroom).put(
                                "planned",
                                expositionRepository.count(
                                        getCountOfPlannedExpositions
                                                .withDateTime(now)
                                                .withShowroom(showroom)
                                )
                        );
                    }

                    request.setAttribute("expositionTypes", types);
                }
        );

        dispatcher(url("mainPageJsp")).forward(request, response);
        logger.info("executed");
    }
}
