package com.flamingo.tests.api.restfulbooker.booking.positive.provider;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingDates;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BookingPositiveArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("Full booking with all fields and breakfast",
                        BookingRequest.builder()
                                .firstname("Jim")
                                .lastname("Brown")
                                .totalPrice(111)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2023-01-01")
                                        .checkout("2023-01-02")
                                        .build())
                                .additionalneeds("Breakfast")
                                .build()),
                Arguments.of("Partial booking with lunch and no deposit",
                        BookingRequest.builder()
                                .firstname("Sally")
                                .lastname("Wilson")
                                .totalPrice(222)
                                .depositPaid(false)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2023-05-10")
                                        .checkout("2023-05-15")
                                        .build())
                                .additionalneeds("Lunch")
                                .build()),
                Arguments.of("High price booking with all inclusive",
                        BookingRequest.builder()
                                .firstname("Mark")
                                .lastname("Jones")
                                .totalPrice(333)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2023-10-20")
                                        .checkout("2023-10-25")
                                        .build())
                                .additionalneeds("All inclusive")
                                .build())
        );
    }
}
