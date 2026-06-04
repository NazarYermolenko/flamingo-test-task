package com.flamingo.framework.api.clients.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GraphQLSpecs {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://countries.trevorblades.com/")
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL)
                .build();
    }
}
