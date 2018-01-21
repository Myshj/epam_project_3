package orm.repository.queries;

import orm.Model;

import java.util.function.Supplier;

/**
 * Base interface for all queries for count of entoties.
 *
 * @param <T>
 */
public interface ICountingQuery<T extends Model> extends Supplier<Long> {
}
