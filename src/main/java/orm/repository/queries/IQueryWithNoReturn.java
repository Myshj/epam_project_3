package orm.repository.queries;

import orm.Model;

import java.util.function.Consumer;

public interface IQueryWithNoReturn<T extends Model> extends Consumer<T> {
}
