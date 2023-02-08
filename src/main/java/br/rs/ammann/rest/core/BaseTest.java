package br.rs.ammann.rest.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;


import static org.hamcrest.Matchers.lessThan;

public class BaseTest implements Constant {
    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;
        RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}