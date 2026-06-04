package com.flamingo.tests.api.restfulbooker.auth;

import com.flamingo.framework.api.controllers.restfulbooker.auth.data.login.AuthErrorResponse;
import com.flamingo.framework.api.controllers.restfulbooker.auth.data.login.AuthRequest;
import com.flamingo.framework.api.controllers.restfulbooker.auth.data.login.AuthResponse;
import com.flamingo.tests.api.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Auth Controller Tests")
public class AuthTest extends BaseApiTest {

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void testLoginSuccess() {
        AuthRequest payload = AuthRequest.builder()
                .username("admin")
                .password("password123")
                .build();

        Response response = api.restfulBooker().auth().login(payload);
        assertThat(response.getStatusCode()).isEqualTo(200);

        AuthResponse authResponse = response.as(AuthResponse.class);
        assertThat(authResponse.getToken()).isNotBlank();
    }

    @Test
    @DisplayName("Should fail to login with invalid credentials")
    void testLoginFailure() {
        AuthRequest payload = AuthRequest.builder()
                .username("invalid")
                .password("invalid")
                .build();

        Response response = api.restfulBooker().auth().login(payload);
        assertThat(response.getStatusCode()).isEqualTo(200); // RestfulBooker returns 200 even for failed login but with "Bad credentials" message

        AuthErrorResponse errorResponse = response.as(AuthErrorResponse.class);
        assertThat(errorResponse.getReason()).isEqualTo("Bad credentials");
    }
}
