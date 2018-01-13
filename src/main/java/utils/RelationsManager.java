package utils;

import models.*;

import java.util.HashMap;
import java.util.Map;

public enum RelationsManager {
    INSTANCE;
    private Map<Class<? extends WebModel>, Map<String, Class<? extends WebModel>>> relatives =
            new HashMap<Class<? extends WebModel>, Map<String, Class<? extends WebModel>>>() {{
                put(City.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("countries", Country.class);
                }});

                put(Building.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("streets", Street.class);
                }});

                put(Country.class, new HashMap<>());

                put(Exposition.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("showrooms", Showroom.class);
                }});

                put(Order.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("tickets", Ticket.class);
                    put("users", User.class);
                    put("orderStates", OrderState.class);
                }});

                put(OrderState.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("tickets", Ticket.class);
                    put("users", User.class);
                    put("orderStates", OrderState.class);
                }});

                put(Showroom.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("buildings", Building.class);
                }});

                put(Street.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("cities", City.class);
                }});

                put(Ticket.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("expositions", Exposition.class);
                    put("ticketTypes", TicketType.class);
                }});

                put(TicketType.class, new HashMap<>());

                put(User.class, new HashMap<String, Class<? extends WebModel>>() {{
                    put("userRoles", UserRole.class);
                }});

                put(UserRole.class, new HashMap<>());
            }};

    public Map<String, Class<? extends WebModel>> get(Class<? extends WebModel> clazz) {
        return relatives.get(clazz);
    }
}
