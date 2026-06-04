package com.flamingo.framework.api.controllers.restfulbooker.booking;

import com.flamingo.framework.api.controllers.BaseController;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.common.BookingRequest;
import com.flamingo.framework.api.controllers.restfulbooker.booking.data.create.BookingResponse;
import com.flamingo.framework.api.clients.specs.RestfulBookerSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingController extends BaseController {

    public BookingController() {
    }

    public Response create(BookingRequest payload) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .body(payload)
                .when()
                .post("/booking");
    }

    public BookingResponse create(BookingRequest payload, int expectedStatusCode) {
        return validateAndExtract(create(payload), expectedStatusCode, BookingResponse.class);
    }

    public Response get(int id) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .when()
                .get("/booking/" + id);
    }

    public BookingRequest get(int id, int expectedStatusCode) {
        return validateAndExtract(get(id), expectedStatusCode, BookingRequest.class);
    }

    public Response update(int id, BookingRequest payload) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .body(payload)
                .when()
                .put("/booking/" + id);
    }

    public Response update(int id, BookingRequest payload, String token) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .header("Cookie", "token=" + token)
                .body(payload)
                .when()
                .put("/booking/" + id);
    }

    public BookingRequest update(int id, BookingRequest payload, String token, int expectedStatusCode) {
        return validateAndExtract(update(id, payload, token), expectedStatusCode, BookingRequest.class);
    }

    public Response delete(int id) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .when()
                .delete("/booking/" + id);
    }

    public Response delete(int id, String token) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + id);
    }
}
