package launch.servlets.managers;

import models.*;
import models.entity_from_request_updaters.*;
import orm.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public enum EntityFromRequestUpdaterManager {
    INSTANCE;

    private Map<Class<? extends Model>, BiConsumer<? extends Model, HttpServletRequest>> converters = new HashMap<>();

    EntityFromRequestUpdaterManager() {
        converters.put(Country.class, new CountryUpdater());
        converters.put(City.class, new CityUpdater());
        converters.put(Street.class, new StreetUpdater());
        converters.put(Building.class, new BuildingUpdater());
        converters.put(Showroom.class, new ShowroomUpdater());
        converters.put(Exposition.class, new ExpositionUpdater());
        converters.put(TicketType.class, new TicketTypeUpdater());
        converters.put(Ticket.class, new TicketUpdater());
    }

    public <T extends Model> BiConsumer<T, HttpServletRequest> get(Class<T> clazz) {
        return (BiConsumer<T, HttpServletRequest>) converters.get(clazz);
    }
}
