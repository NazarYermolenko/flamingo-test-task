package com.flamingo.framework.api.clients;

import com.flamingo.framework.api.controllers.restfulbooker.auth.AuthController;
import com.flamingo.framework.api.controllers.restfulbooker.booking.BookingController;

public class RestfulBookerClient {
    private AuthController auth;
    private BookingController booking;

    RestfulBookerClient() {
    }

    public AuthController auth() {
        if (auth == null) {
            auth = new AuthController();
        }
        return auth;
    }

    public BookingController booking() {
        if (booking == null) {
            booking = new BookingController();
        }
        return booking;
    }
}
