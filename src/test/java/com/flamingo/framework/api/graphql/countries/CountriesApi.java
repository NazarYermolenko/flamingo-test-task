package com.flamingo.framework.api.graphql.countries;

import com.flamingo.framework.api.controllers.countries.CountriesController;
import com.flamingo.framework.api.controllers.countries.data.Country;
import com.flamingo.framework.api.controllers.countries.data.query.GraphQLErrorResponse;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CountriesApi {

    private final CountriesController controller;

    public CountriesApi(CountriesController controller) {
        this.controller = controller;
    }

    public List<Country> getCountriesByCodes(String... codes) {
        Response response = controller.query(
                CountriesQuery.GET_LIMITED_COUNTRIES.document(),
                Map.of("codes", List.of(codes))
        );
        assertOk(response);
        return extractCountries(response);
    }

    public List<Country> getAllCountries() {
        Response response = controller.query(CountriesQuery.GET_COUNTRIES.document());
        assertOk(response);
        return extractCountries(response);
    }

    public Country getCountry(String code) {
        Response response = controller.query(
                CountriesQuery.GET_COUNTRY.document(),
                Map.of("code", code)
        );
        assertOk(response);
        return response.jsonPath().getObject("data.country", Country.class);
    }

    public Country findCountryByCode(String code) {
        Response response = controller.query(
                CountriesQuery.GET_COUNTRY_NAME_ONLY.document(),
                Map.of("code", code)
        );
        assertOk(response);
        return response.jsonPath().getObject("data.country", Country.class);
    }

    public List<Country> getCountriesWithFragment() {
        Response response = controller.query(CountriesQuery.GET_COUNTRIES_WITH_FRAGMENT.document());
        assertOk(response);
        return extractCountries(response);
    }

    public GraphQLErrorResponse queryWithInvalidField(String code) {
        Response response = controller.query(
                CountriesQuery.GET_COUNTRY_INVALID_FIELD.document(),
                Map.of("code", code)
        );
        assertOk(response);
        return response.as(GraphQLErrorResponse.class);
    }

    public GraphQLErrorResponse executeMalformedQuery() {
        Response response = controller.query(CountriesQuery.MALFORMED_GET_COUNTRY.document());
        assertOk(response);
        return response.as(GraphQLErrorResponse.class);
    }

    private static List<Country> extractCountries(Response response) {
        return response.jsonPath().getList("data.countries", Country.class);
    }

    private static void assertOk(Response response) {
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
