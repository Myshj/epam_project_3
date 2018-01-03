package models.request_to_model_converters;

import orm.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

abstract class RequestToModelConverter<T extends Model> implements Function<HttpServletRequest, T> {
}
