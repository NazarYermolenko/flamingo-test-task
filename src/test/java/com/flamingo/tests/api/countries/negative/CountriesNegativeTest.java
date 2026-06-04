package com.flamingo.tests.api.countries.negative;

import com.flamingo.framework.api.controllers.countries.data.query.GraphQLErrorResponse;
import com.flamingo.tests.api.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Countries GraphQL API — Negative Tests")
public class CountriesNegativeTest extends BaseApiTest {

    @Test
    @DisplayName("Query country with invalid code")
    void returnsNullCountryForInvalidCode() {
        var country = api.countries().findCountryByCode("INVALID");

        assertThat(country).isNull();
    }

    @Test
    @DisplayName("Malformed GraphQL query")
    void returnsErrorsForMalformedQuery() {
        GraphQLErrorResponse response = api.countries().executeMalformedQuery();

        assertThat(response.getErrors()).isNotEmpty();
        assertThat(response.getErrors().get(0).getMessage()).isNotBlank();
        assertThat(response.getData()).isNull();
    }

    @Test
    @DisplayName("Query non-existent field")
    void returnsErrorsForNonExistentField() {
        GraphQLErrorResponse response = api.countries().queryWithInvalidField("UA");

        assertThat(response.getErrors()).isNotEmpty();
        assertThat(response.getErrors().get(0).getMessage()).contains("nonExistentField");
        assertThat(response.getData()).isNull();
    }
}
