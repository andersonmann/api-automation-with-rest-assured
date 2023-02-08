package br.rs.ammann.rest.tasks;

import br.rs.ammann.rest.core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RestrictionsConsultTest extends BaseTest {

    @Test
    public void cpfWithRestrictionTest() {
        given()
        .when()
                .get("restricoes/97093236014")
        .then()
                .statusCode(200)
                .body("mensagem", is("O CPF 97093236014 tem problema"))
        ;
    }

    @Test
    public void cpfWithOutRestrictionTest() {
        given()
        .when()
                .get("restricoes/17822386034")
        .then()
                .statusCode(204)
        ;
    }
}