package launch.servlets.managers;

import models.*;
import models.request_to_model_converters.*;
import orm.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum RequestToModelConverterManager {
    INSTANCE;

    private Map<Class<? extends Model>, Function<HttpServletRequest, ? extends Model>> converters = new HashMap<>();

    RequestToModelConverterManager() {
        converters.put(Country.class, new CountryConverter());
        converters.put(City.class, new CityConverter());
        converters.put(Street.class, new StreetConverter());
        converters.put(Building.class, new BuildingConverter());
        converters.put(Showroom.class, new ShowroomConverter());
        converters.put(Exposition.class, new ExpositionConverter());
        converters.put(TicketType.class, new TicketTypeConverter());
        converters.put(Ticket.class, new TicketConverter());
        converters.put(UserRole.class, new UserRoleConverter());
    }

    public <T extends Model> Function<HttpServletRequest, T> get(Class<T> clazz) {
        return (Function<HttpServletRequest, T>) converters.get(clazz);
    }
}
