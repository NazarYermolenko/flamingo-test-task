package com.flamingo.framework.api.controllers;

import com.flamingo.framework.api.clients.specs.RestfulBookerSpecs;
import io.restassured.response.Response;

public abstract class BaseController {

    protected <T> T validateAndExtract(Response response, int expectedStatusCode, Class<T> responseClass) {
        return response.then()
                .spec(RestfulBookerSpecs.responseSpec(expectedStatusCode))
                .extract()
                .as(responseClass);
    }
}
