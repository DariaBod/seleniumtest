package org.dariabodiakova.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.dariabodiakova.utils.AppConfig;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected final RequestSpecification requestSpec;

    public BaseApiClient() {
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(AppConfig.getBaseApiUrl())
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected Response post(String endpoint, Object body) {
        return given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response get(String endpoint) {
        return given()
                .spec(requestSpec)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}