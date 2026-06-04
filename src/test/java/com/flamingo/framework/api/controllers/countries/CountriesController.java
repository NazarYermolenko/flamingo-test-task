package com.flamingo.framework.api.controllers.countries;

import com.flamingo.framework.api.controllers.countries.data.query.CountriesRequest;
import com.flamingo.framework.api.clients.specs.GraphQLSpecs;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CountriesController {

    public CountriesController() {
    }

    public Response query(String query) {
        return query(query, Map.of());
    }

    public Response query(String query, Map<String, Object> variables) {
        CountriesRequest payload = CountriesRequest.builder()
                .query(query)
                .variables(variables)
                .build();

        return given()
                .spec(GraphQLSpecs.requestSpec())
                .body(payload)
                .when()
                .post();
    }
}
