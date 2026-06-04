package com.flamingo.tests.api.restfulbooker.booking.negative.provider;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingDates;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BookingNegativeArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("Create booking with total price 0",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("Doe")
                                .totalPrice(0)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking with negative total price",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("Doe")
                                .totalPrice(-10)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking with empty firstname",
                        BookingRequest.builder()
                                .firstname("")
                                .lastname("Doe")
                                .totalPrice(100)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking with empty lastname",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("")
                                .totalPrice(100)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking with null firstname",
                        BookingRequest.builder()
                                .firstname(null)
                                .lastname("Doe")
                                .totalPrice(100)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking with null lastname",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname(null)
                                .totalPrice(100)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking without booking dates",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("Doe")
                                .totalPrice(100)
                                .depositPaid(true)
                                .bookingdates(null)
                                .build()),
                Arguments.of("Create booking without total price",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("Doe")
                                .totalPrice(null)
                                .depositPaid(true)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build()),
                Arguments.of("Create booking without deposit paid status",
                        BookingRequest.builder()
                                .firstname("John")
                                .lastname("Doe")
                                .totalPrice(100)
                                .depositPaid(null)
                                .bookingdates(BookingDates.builder()
                                        .checkin("2024-01-01")
                                        .checkout("2024-01-02")
                                        .build())
                                .build())
        );
    }
}
