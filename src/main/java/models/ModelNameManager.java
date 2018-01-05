package models;

import orm.Model;

import java.util.HashMap;
import java.util.Map;

public enum ModelNameManager {
    INSTANCE;

    private Map<Class<? extends Model>, String> singularNames = new HashMap<Class<? extends Model>, String>() {{
        put(City.class, "city");
        put(Country.class, "country");
        put(Exposition.class, "exposition");
        put(Order.class, "order");
        put(OrderState.class, "orderStates");
        put(Showroom.class, "showroom");
        put(Street.class, "street");
        put(Ticket.class, "ticket");
        put(TicketType.class, "ticketType");
        put(User.class, "user");
        put(UserRole.class, "userRole");
    }};
    private Map<Class<? extends Model>, String> pluralNames = new HashMap<Class<? extends Model>, String>() {{
        put(City.class, "cities");
        put(Country.class, "countries");
        put(Exposition.class, "expositions");
        put(Order.class, "orders");
        put(OrderState.class, "orderStates");
        put(Showroom.class, "showrooms");
        put(Street.class, "streets");
        put(Ticket.class, "tickets");
        put(TicketType.class, "ticketTypes");
        put(User.class, "users");
        put(UserRole.class, "userRoles");
    }};

    public String singularName(Class<? extends Model> clazz) {
        return singularNames.get(clazz);
    }

    public String pluralName(Class<? extends Model> clazz) {
        return pluralNames.get(clazz);
    }
}
