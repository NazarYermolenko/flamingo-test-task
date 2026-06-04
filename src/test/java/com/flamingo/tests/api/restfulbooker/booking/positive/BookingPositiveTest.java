package com.flamingo.tests.api.restfulbooker.booking.positive;

import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.create.BookingResponse;
import com.flamingo.tests.api.restfulbooker.BaseRestfulBookerTest;
import com.flamingo.tests.api.restfulbooker.booking.positive.provider.BookingPositiveArgumentsProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Booking Controller Positive Tests")
public class BookingPositiveTest extends BaseRestfulBookerTest {

    @ParameterizedTest(name = "{index} => {0}")
    @ArgumentsSource(BookingPositiveArgumentsProvider.class)
    @DisplayName("Data-driven CRUD operations on booking")
    void testBookingCrudOperations(String description, BookingRequest payload) {
        // 1. Create Booking
        BookingResponse createdBooking = api.restfulBooker().booking().create(payload, 200);
        Integer bookingId = createdBooking.getBookingid();
        assertThat(bookingId).isNotNull();
        assertThat(createdBooking.getBooking().getFirstname()).isEqualTo(payload.getFirstname());

        // 2. Get Booking
        BookingRequest fetchedBooking = api.restfulBooker().booking().get(bookingId, 200);
        assertThat(fetchedBooking.getFirstname()).isEqualTo(payload.getFirstname());
        assertThat(fetchedBooking.getLastname()).isEqualTo(payload.getLastname());

        // 3. Update Booking
        payload.setFirstname(payload.getFirstname() + " Updated");
        BookingRequest updatedBooking = api.restfulBooker().booking().update(bookingId, payload, token, 200);
        assertThat(updatedBooking.getFirstname()).isEqualTo(payload.getFirstname());

        // 4. Delete Booking
        Response deleteResponse = api.restfulBooker().booking().delete(bookingId, token);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

        // 5. Verify Deletion
        Response getAfterDeleteResponse = api.restfulBooker().booking().get(bookingId);
        assertThat(getAfterDeleteResponse.getStatusCode()).isEqualTo(404);
    }
}
