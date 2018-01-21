package orm.repository.queries;

import orm.Model;

import java.util.List;
import java.util.function.Supplier;

/**
 * Base interface for all entities returning list of entities.
 *
 * @param <T>
 */
public interface IListEntitiesQuery<T extends Model> extends Supplier<List<T>> {
}
