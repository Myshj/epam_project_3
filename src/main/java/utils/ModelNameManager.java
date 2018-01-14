package utils;

import models.*;
import orm.Model;
import utils.meta.ModelNames;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ModelNameManager {
    INSTANCE;

    private Map<Class<? extends Model>, ModelNames> names = new HashMap<Class<? extends Model>, ModelNames>() {{
        put(City.class, new ModelNames("city", "cities"));
        put(Building.class, new ModelNames("building", "buildings"));
        put(Country.class, new ModelNames("country", "countries"));
        put(Exposition.class, new ModelNames("exposition", "expositions"));
        put(Order.class, new ModelNames("order", "orders"));
        put(OrderState.class, new ModelNames("orderState", "orderStates"));
        put(Showroom.class, new ModelNames("showroom", "showrooms"));
        put(Street.class, new ModelNames("street", "streets"));
        put(Ticket.class, new ModelNames("ticket", "tickets"));
        put(TicketType.class, new ModelNames("ticketType", "ticketTypes"));
        put(User.class, new ModelNames("user", "users"));
        put(UserRole.class, new ModelNames("userRole", "userRoles"));
    }};


    public String singularName(Class<? extends Model> clazz) {
        return names.get(clazz).getSingular();
    }

    public String pluralName(Class<? extends Model> clazz) {
        return names.get(clazz).getPlural();
    }


    public Set<Class<? extends Model>> classes() {
        return names.keySet();
    }
}
