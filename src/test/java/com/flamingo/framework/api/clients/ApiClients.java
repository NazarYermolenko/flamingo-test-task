package com.flamingo.framework.api.clients;

import com.flamingo.framework.api.controllers.countries.CountriesController;
import com.flamingo.framework.api.graphql.countries.CountriesApi;

public class ApiClients {
    private RestfulBookerClient restfulBooker;
    private CountriesApi countries;

    public RestfulBookerClient restfulBooker() {
        if (restfulBooker == null) {
            restfulBooker = new RestfulBookerClient();
        }
        return restfulBooker;
    }

    public CountriesApi countries() {
        if (countries == null) {
            countries = new CountriesApi(new CountriesController());
        }
        return countries;
    }
}
