package orm.repository.queries;

import orm.Model;

import java.util.function.Consumer;

/**
 * Base interface for all queries returning nothing.
 *
 * @param <T>
 */
public interface IQueryWithNoReturn<T extends Model> extends Consumer<T> {
}
