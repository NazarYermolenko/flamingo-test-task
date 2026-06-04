package com.flamingo.tests.api.restfulbooker.booking.negative.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BookingUnauthorizedArgumentsProvider implements ArgumentsProvider {

    public enum ProtectedOperation {
        UPDATE,
        DELETE
    }

    public enum AuthToken {
        MISSING,
        INVALID
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("PUT /booking/{id} without token", ProtectedOperation.UPDATE, AuthToken.MISSING),
                Arguments.of("PUT /booking/{id} with invalid token", ProtectedOperation.UPDATE, AuthToken.INVALID),
                Arguments.of("DELETE /booking/{id} without token", ProtectedOperation.DELETE, AuthToken.MISSING),
                Arguments.of("DELETE /booking/{id} with invalid token", ProtectedOperation.DELETE, AuthToken.INVALID)
        );
    }
}
