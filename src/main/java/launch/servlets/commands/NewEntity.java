package launch.servlets.commands;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewEntity<T extends Model> extends ServletCommand<T> {
    private String name;

    public NewEntity(
            HttpServlet servlet,
            Repository<T> repository,
            String name
    ) {
        super(servlet, repository);
        this.name = name;
    }

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            dispatcher(
                    String.format(
                            "/jsp/new-%s.jsp",
                            name
                    )

            ).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
}
