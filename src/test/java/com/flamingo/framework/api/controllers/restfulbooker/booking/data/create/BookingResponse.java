package com.flamingo.framework.api.controllers.restfulbooker.booking.data.create;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import lombok.Data;

@Data
public class BookingResponse {
    private Integer bookingid;
    private BookingRequest booking;
}
