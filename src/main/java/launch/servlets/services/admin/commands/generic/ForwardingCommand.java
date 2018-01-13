package launch.servlets.services.admin.commands.generic;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.http.HttpServlet;

public abstract class ForwardingCommand<T extends Model> extends ModelCommand<T> {
    protected final ShowList<T> showList;

    public ForwardingCommand(
            HttpServlet servlet,
            Repository<T> repository,
            ShowList<T> showList
    ) {
        super(servlet, repository);
        this.showList = showList;
    }
}
