package models.request_to_model_converters;

import models.TicketType;

import javax.servlet.http.HttpServletRequest;

public class TicketTypeConverter extends RequestToModelConverter<TicketType> {
    @Override
    public TicketType apply(HttpServletRequest request) {
        return new TicketType(
                request.getParameter("name")
        );
    }
}
