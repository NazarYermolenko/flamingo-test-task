package com.flamingo.framework.api.controllers.restfulbooker.booking.data.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {
    private String checkin;
    private String checkout;
}
