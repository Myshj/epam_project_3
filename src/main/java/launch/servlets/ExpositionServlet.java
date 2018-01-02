package launch.servlets;

import launch.servlets.commands.IncludeListToRequest;
import launch.servlets.commands.SearchExpositionsByNameAndShowroomName;
import models.Exposition;
import models.Showroom;
import orm.RepositoryManager;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ExpositionServlet",
        urlPatterns = {"/exposition"}
)
public class ExpositionServlet extends ModelServlet<Exposition> {

    private final Repository<Showroom> showroomRepository = RepositoryManager.INSTANCE.get(Showroom.class);

    private final IncludeListToRequest<Showroom> includeShowrooms = new IncludeListToRequest<>(
            this,
            "showrooms"
    );

    private final SearchExpositionsByNameAndShowroomName searchByNameAndShowroomName = new SearchExpositionsByNameAndShowroomName(
            clazz(),
            this,
            repository,
            forwardList
    );

    @Override
    protected Class<Exposition> clazz() {
        return Exposition.class;
    }

    @Override
    protected String singularName() {
        return "exposition";
    }

    @Override
    protected String pluralName() {
        return "expositions";
    }

    @Override
    protected String removedSuccessfullyMessage() {
        return "The exposition removed successfully.";
    }

    @Override
    protected String createdSuccessfullyMessage() {
        return "The exposition created successfully.";
    }

    @Override
    protected String updatedSuccessfullyMessage() {
        return "The exposition updated successfully.";
    }

    @Override
    public void init() throws ServletException {
        super.init();
        getActions.put("searchByNameAndShowroomName", this::onSearchByNameAndShowroomName);
    }

    @Override
    protected void onSearchById(HttpServletRequest request, HttpServletResponse response) {
        try {
            includeShowrooms.withList(showroomRepository.getAll()).execute(request, response);
            super.onSearchById(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onNewEntity(HttpServletRequest req, HttpServletResponse resp) {
        try {
            includeShowrooms.withList(showroomRepository.getAll()).execute(req, resp);
            super.onNewEntity(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void onSearchByNameAndShowroomName(HttpServletRequest request, HttpServletResponse response){
        try {
            searchByNameAndShowroomName.execute(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
