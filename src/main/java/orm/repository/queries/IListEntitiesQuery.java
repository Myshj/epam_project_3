package orm.repository.queries;

import orm.Model;

import java.util.List;
import java.util.function.Supplier;

public interface IListEntitiesQuery<T extends Model> extends Supplier<List<T>> {
}
