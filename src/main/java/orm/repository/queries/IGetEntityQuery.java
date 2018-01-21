package orm.repository.queries;

import orm.Model;

import java.util.function.Supplier;

public interface IGetEntityQuery<T extends Model> extends Supplier<T> {
}
