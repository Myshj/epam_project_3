package orm.repository.queries;

import orm.Model;

import java.util.function.Supplier;

/**
 * Base interface for all queries returning single entity.
 *
 * @param <T>
 */
public interface IGetEntityQuery<T extends Model> extends Supplier<T> {
}
