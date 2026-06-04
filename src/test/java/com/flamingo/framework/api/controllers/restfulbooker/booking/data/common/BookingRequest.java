package com.flamingo.framework.api.controllers.restfulbooker.booking.data.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private String firstname;
    private String lastname;
    @JsonProperty("totalprice")
    private Integer totalPrice;
    @JsonProperty("depositpaid")
    private Boolean depositPaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
