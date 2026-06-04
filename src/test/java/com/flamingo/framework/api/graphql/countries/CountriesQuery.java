package com.flamingo.framework.api.graphql.countries;

import com.flamingo.framework.api.graphql.GraphQLDocumentLoader;

public enum CountriesQuery {
    GET_LIMITED_COUNTRIES("graphql/countries/get-limited-countries.graphql"),
    GET_COUNTRIES("graphql/countries/get-countries.graphql"),
    GET_COUNTRY("graphql/countries/get-country.graphql"),
    GET_COUNTRY_NAME_ONLY("graphql/countries/get-country-name-only.graphql"),
    GET_COUNTRIES_WITH_FRAGMENT("graphql/countries/get-countries-with-fragment.graphql"),
    GET_COUNTRY_INVALID_FIELD("graphql/countries/get-country-invalid-field.graphql"),
    MALFORMED_GET_COUNTRY("graphql/countries/malformed-get-country.graphql");

    private final String resourcePath;
    private final String document;

    CountriesQuery(String resourcePath) {
        this.resourcePath = resourcePath;
        this.document = GraphQLDocumentLoader.load(resourcePath);
    }

    public String document() {
        return document;
    }

    public String resourcePath() {
        return resourcePath;
    }
}
