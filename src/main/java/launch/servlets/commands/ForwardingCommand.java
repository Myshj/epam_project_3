package launch.servlets.commands;

import orm.Model;
import orm.repository.Repository;

import javax.servlet.http.HttpServlet;

public abstract class ForwardingCommand<T extends Model> extends ServletCommand<T> {
    protected final ForwardList<T> forwardList;

    public ForwardingCommand(
            HttpServlet servlet,
            Repository<T> repository,
            ForwardList<T> forwardList
    ) {
        super(servlet, repository);
        this.forwardList = forwardList;
    }
}
