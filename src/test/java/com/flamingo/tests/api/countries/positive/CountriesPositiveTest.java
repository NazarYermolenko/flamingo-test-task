package com.flamingo.tests.api.countries.positive;

import com.flamingo.framework.api.controllers.countries.data.Country;
import com.flamingo.tests.api.BaseApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Countries GraphQL API — Positive Tests")
public class CountriesPositiveTest extends BaseApiTest {

    @Test
    @DisplayName("Query a limited list of countries using filter variables")
    void returnsFilteredCountriesByCodes() {
        List<Country> countries = api.countries().getCountriesByCodes("UA", "US", "GB");

        assertThat(countries).hasSize(3);
        assertThat(countries)
                .extracting(Country::getCode)
                .containsExactlyInAnyOrder("UA", "US", "GB");
    }

    @Test
    @DisplayName("Query multiple countries with continent details")
    void returnsCountriesWithContinentDetails() {
        List<Country> countries = api.countries().getAllCountries();

        assertThat(countries).isNotEmpty();
        assertThat(countries.get(0).getName()).isNotBlank();
        assertThat(countries.get(0).getContinent().getName()).isNotBlank();
    }

    @Test
    @DisplayName("Query a single country using variables")
    void returnsSingleCountryByCode() {
        Country country = api.countries().getCountry("UA");

        assertThat(country.getName()).isEqualTo("Ukraine");
        assertThat(country.getCapital()).isEqualTo("Kyiv");
    }

    @Test
    @DisplayName("Query countries using GraphQL fragments")
    void returnsCountriesUsingFragment() {
        List<Country> countries = api.countries().getCountriesWithFragment();

        assertThat(countries).hasSize(2);
        assertThat(countries)
                .extracting(Country::getName)
                .containsExactlyInAnyOrder("Ukraine", "United States");
    }
}
