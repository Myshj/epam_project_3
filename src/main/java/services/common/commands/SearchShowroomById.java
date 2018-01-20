package services.common.commands;

import models.Exposition;
import models.Showroom;
import models.commands.FindExpositionsByShowroom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.queries.SqlQueryContext;
import services.ServletServiceContext;
import services.admin.commands.generic.includers.IncludeAddress;
import services.admin.commands.generic.includers.IncludeListToRequest;
import services.commands.ServletCommand;
import utils.meta.MetaInfoManager;
import utils.transactions.TransactionExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command to search showroom, its address and expositions by a given id of showroon.
 */
public class SearchShowroomById extends ServletCommand {
    private static final Logger logger = LogManager.getLogger(SearchShowroomById.class);

    private IncludeListToRequest<Exposition> expositionIncluder = new IncludeListToRequest<>(
            context,
            Exposition.class,
            MetaInfoManager.INSTANCE.get(Exposition.class).getNames().getPlural()
    );

    private IncludeAddress addressIncluder;// = new IncludeAddress(this.servletContext, "address");

    private FindExpositionsByShowroom expositionFinder;

    public SearchShowroomById(ServletServiceContext context) {
        super(context);
        logger.info("started construction");
        expositionFinder = new FindExpositionsByShowroom(
                new SqlQueryContext<>(
                        Exposition.class,
                        context.getManagers().getRepository(),
                        context.getManagers().getConnection().get()
                )
                //ConnectionServiceProvider.INSTANCE.get()
        );
        addressIncluder = new IncludeAddress(context);
        logger.info("constructed");
    }

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("started execution");

        new TransactionExecutor(context.getManagers().getConnection().get()).apply(
                () -> {

                    Showroom showroom = context.getManagers().getRepository().get(Showroom.class).getById(
                            Long.valueOf(request.getParameter("id"))
                    ).orElse(null);
                    request.setAttribute("showroom", showroom);

                    if (showroom != null) {
                        List<Exposition> expositions = expositionFinder.withShowroom(showroom).get();
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

                }
        );

        dispatcher(url("observeShowroomJsp")).forward(request, response);
        logger.info("executed");
    }
}
