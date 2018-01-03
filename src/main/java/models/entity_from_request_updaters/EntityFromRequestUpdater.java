package models.entity_from_request_updaters;

import orm.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.function.BiConsumer;

abstract class EntityFromRequestUpdater<T extends Model> implements BiConsumer<T, HttpServletRequest> {
}
