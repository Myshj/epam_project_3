package orm.repository.impl.sql.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import orm.Model;
import orm.repository.queries.IListEntitiesQuery;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Base class for all queries querying database for lists of entities.
 *
 * @param <T>
 */
public abstract class ListEntitiesQuery<T extends Model> extends DbQuery<T> implements IListEntitiesQuery<T> {
    private static final Logger logger = LogManager.getLogger(ListEntitiesQuery.class);

    public ListEntitiesQuery(
            SqlQueryContext<T> context,
            String sql
    ) {
        super(context, sql);
        logger.info("constructed");
    }

    @Override
    public List<T> get() {
        try {
            return execute();
        } catch (Exception e) {
            logger.error("exception during execution --> return empty list");
            logger.error(e);
            return new ArrayList<>();
        }
    }

    /**
     *
     * @return returned entities or empty list if SQLException occured.
     */
    protected final List<T> execute() throws Exception {
        logger.info("started execution");
        List<T> result = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Optional.ofNullable(converter.apply(rs)).ifPresent(result::add);
            }
            return result;
        }
    }
}
