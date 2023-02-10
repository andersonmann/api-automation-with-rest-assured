package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
/**
 * @author anderson.mann
 *
 */

public class SchemaTest extends BaseTest {

    @Test(description = "Valida o contrato do recurso simulacoes")
    public void validateSchemaTest(){
        given()
        .when()
                .get("simulacoes")
        .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("simulation.json"))
        ;
    }
}