package models.request_to_model_converters;

import models.TicketType;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

public class TicketTypeConverter implements Function<HttpServletRequest, TicketType> {
    @Override
    public TicketType apply(HttpServletRequest request) {
        return new TicketType(
                request.getParameter("name")
        );
    }
}
