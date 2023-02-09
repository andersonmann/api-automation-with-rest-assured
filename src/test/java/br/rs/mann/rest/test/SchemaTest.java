package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SchemaTest extends BaseTest {

    @Test
    public void validateSchemaTest(){
        given()
                .log().all()
        .when()
                .get("simulacoes")
        .then()
//                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("simulation.json"))
        ;
    }
}
