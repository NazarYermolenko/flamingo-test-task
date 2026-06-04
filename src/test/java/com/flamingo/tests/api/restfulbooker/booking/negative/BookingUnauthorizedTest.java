package com.flamingo.tests.api.restfulbooker.booking.negative;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingDates;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.create.BookingResponse;
import com.flamingo.tests.api.restfulbooker.BaseRestfulBookerTest;
import com.flamingo.tests.api.restfulbooker.booking.negative.provider.BookingUnauthorizedArgumentsProvider;
import com.flamingo.tests.api.restfulbooker.booking.negative.provider.BookingUnauthorizedArgumentsProvider.AuthToken;
import com.flamingo.tests.api.restfulbooker.booking.negative.provider.BookingUnauthorizedArgumentsProvider.ProtectedOperation;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Booking Controller Unauthorized Access Tests")
public class BookingUnauthorizedTest extends BaseRestfulBookerTest {

    private static final String INVALID_TOKEN = "invalid-token";

    @ParameterizedTest(name = "{index} => {0}")
    @ArgumentsSource(BookingUnauthorizedArgumentsProvider.class)
    @DisplayName("Should return 403 when protected booking endpoint is called without valid auth")
    void testProtectedEndpointRejectsUnauthorizedAccess(
            String description,
            ProtectedOperation operation,
            AuthToken authToken) {
        int bookingId = createBooking();
        BookingRequest payload = sampleBookingPayload();

        Response response = switch (operation) {
            case UPDATE -> authToken == AuthToken.MISSING
                    ? api.restfulBooker().booking().update(bookingId, payload)
                    : api.restfulBooker().booking().update(bookingId, payload, INVALID_TOKEN);
            case DELETE -> authToken == AuthToken.MISSING
                    ? api.restfulBooker().booking().delete(bookingId)
                    : api.restfulBooker().booking().delete(bookingId, INVALID_TOKEN);
        };

        assertThat(response.getStatusCode())
                .withFailMessage("Scenario: " + description + ". Expected 403 Forbidden, but got " + response.getStatusCode())
                .isEqualTo(403);
    }

    private int createBooking() {
        BookingResponse createdBooking = api.restfulBooker().booking().create(sampleBookingPayload(), 200);
        assertThat(createdBooking.getBookingid()).isNotNull();
        return createdBooking.getBookingid();
    }

    private BookingRequest sampleBookingPayload() {
        return BookingRequest.builder()
                .firstname("Auth")
                .lastname("Test")
                .totalPrice(100)
                .depositPaid(false)
                .bookingdates(BookingDates.builder()
                        .checkin("2024-06-01")
                        .checkout("2024-06-02")
                        .build())
                .build();
    }
}
