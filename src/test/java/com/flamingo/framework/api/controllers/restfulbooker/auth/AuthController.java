package com.flamingo.framework.api.controllers.restfulbooker.auth;

import com.flamingo.framework.api.controllers.BaseController;
import com.flamingo.framework.api.controllers.restfulbooker.auth.data.login.AuthRequest;
import com.flamingo.framework.api.controllers.restfulbooker.auth.data.login.AuthResponse;
import com.flamingo.framework.api.clients.specs.RestfulBookerSpecs;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthController extends BaseController {

    public AuthController() {
    }

    public Response login(AuthRequest payload) {
        return given()
                .spec(RestfulBookerSpecs.requestSpec())
                .body(payload)
                .when()
                .post("/auth");
    }

    public String login(String username, String password) {
        AuthRequest payload = AuthRequest.builder()
                .username(username)
                .password(password)
                .build();

        return validateAndExtract(login(payload), 200, AuthResponse.class)
                .getToken();
    }
}
