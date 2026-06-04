package com.flamingo.tests.api.restfulbooker.booking.negative;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingDates;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import com.flamingo.tests.api.restfulbooker.BaseRestfulBookerTest;
import com.flamingo.tests.api.restfulbooker.booking.negative.provider.BookingNegativeArgumentsProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Booking Controller Negative Tests")
public class BookingNegativeTest extends BaseRestfulBookerTest {

    @ParameterizedTest(name = "{index} => {0}")
    @ArgumentsSource(BookingNegativeArgumentsProvider.class)
    @DisplayName("Should return 400 Bad Request for invalid booking data")
    void testCreateBookingWithInvalidData(String description, BookingRequest payload) {
        Response response = api.restfulBooker().booking().create(payload);

        // TODO: Open defects for the following validation issues:
        // 1. Total price 0/negative returns 200 OK
        // 2. Empty firstname/lastname returns 200 OK
        // 3. Null firstname/lastname returns 500 Internal Server Error
        // 4. Missing bookingdates returns 500 Internal Server Error
        // 5. Missing totalprice/depositpaid returns 500 Internal Server Error

        // As seen in existing tests, the API often incorrectly returns 200 or 500
        // We expect 400 Bad Request for invalid input data
        assertThat(response.getStatusCode())
                .withFailMessage("Scenario: " + description + ". Expected 400 Bad Request, but got " + response.getStatusCode())
                .isEqualTo(400);
    }

    @Test
    @DisplayName("Should return 404 when getting non-existent booking")
    void testGetNonExistentBooking() {
        Response response = api.restfulBooker().booking().get(999999);
        assertThat(response.getStatusCode()).isEqualTo(404);
    }

    @Test
    @DisplayName("Should return 400 when check-in date is after check-out date")
    void testCreateBookingWithInvalidDates() {
        BookingRequest payload = BookingRequest.builder()
                .firstname("Invalid")
                .lastname("Dates")
                .totalPrice(100)
                .depositPaid(true)
                .bookingdates(BookingDates.builder()
                        .checkin("2024-01-10")
                        .checkout("2024-01-01")
                        .build())
                .build();

        Response response = api.restfulBooker().booking().create(payload);
        // TODO: Open defect - API returns 200 OK for invalid date ranges (check-in after check-out)
        // This test correctly identifies a bug in the Restful-booker API:
        // The API should return 400 Bad Request for invalid date ranges, but it returns 200 OK.
        assertThat(response.getStatusCode())
                .withFailMessage("Expected 400 Bad Request for invalid dates, but got " + response.getStatusCode())
                .isEqualTo(400);
    }
}
