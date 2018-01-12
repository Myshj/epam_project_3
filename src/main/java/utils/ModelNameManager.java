package utils;

import models.*;
import orm.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ModelNameManager {
    INSTANCE;

    private static class Names {
        public final String singular;
        public final String plural;

        public Names(String singular, String plural) {
            this.singular = singular;
            this.plural = plural;
        }
    }

    private Map<Class<? extends Model>, Names> names = new HashMap<Class<? extends Model>, Names>() {{
        put(City.class, new Names("city", "cities"));
        put(Country.class, new Names("country", "countries"));
        put(Exposition.class, new Names("exposition", "expositions"));
        put(Order.class, new Names("order", "orders"));
        put(OrderState.class, new Names("orderState", "orderStates"));
        put(Showroom.class, new Names("showroom", "showrooms"));
        put(Street.class, new Names("street", "streets"));
        put(Ticket.class, new Names("ticket", "tickets"));
        put(TicketType.class, new Names("ticketType", "ticketTypes"));
        put(User.class, new Names("user", "users"));
        put(UserRole.class, new Names("userRole", "userRoles"));
    }};


    public String singularName(Class<? extends Model> clazz) {
        return names.get(clazz).singular;
    }

    public String pluralName(Class<? extends Model> clazz) {
        return names.get(clazz).plural;
    }


    public Set<Class<? extends Model>> classes() {
        return names.keySet();
    }
}
