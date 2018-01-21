package orm.repository.queries;

import orm.Model;

import java.util.function.Supplier;

public interface ICountingQuery<T extends Model> extends Supplier<Long> {
}
