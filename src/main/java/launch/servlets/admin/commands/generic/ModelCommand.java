package launch.servlets.admin.commands.generic;

import launch.servlets.general.commands.ServletCommand;
import orm.Model;
import orm.repository.Repository;

import javax.servlet.http.HttpServlet;

public abstract class ModelCommand<T extends Model> extends ServletCommand {
    protected Repository<T> repository;

    public ModelCommand(HttpServlet servlet, Repository<T> repository) {
        super(servlet);
        this.repository = repository;
    }

}
