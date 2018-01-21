package services.commands.includers;

import orm.Model;
import orm.repository.IRepository;
import services.ServletServiceContext;
import services.admin.commands.generic.ModelCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Includes part of entities specified by current page and page size to request.
 *
 * @param <T>
 */
public class PaginatedIncluder<T extends Model> extends ModelCommand<T> {
    private IncludeListToRequest<T> includeList;

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterPage = request.getParameter("page");
        String parameterPageSize = request.getParameter("pageSize");


        int currentPage = parameterPage == null ? 1 : Integer.valueOf(parameterPage);
        if (currentPage < 1) {
            currentPage = 1;
        }
        int pageSize = parameterPageSize == null ? 5 : Integer.valueOf(parameterPageSize);
        if (pageSize < 1) {
            pageSize = 1;
        }

        int startingIndex = (currentPage - 1) * pageSize;

        IRepository<T> repository = context.getManagers().getRepository().get(clazz);

        List<T> entities = repository.getAll();

        entities = entities.subList(
                startingIndex,
                Math.min(entities.size(), startingIndex + pageSize)
        );

        includeList.withList(entities).accept(request, response);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute(
                "countOfPages",
                Math.ceil((double) repository.count() / pageSize)
        );
    }

    public PaginatedIncluder(ServletServiceContext context, Class<T> clazz, String name) {
        super(context, clazz);
        includeList = new IncludeListToRequest<>(context, clazz, name);
    }
}
