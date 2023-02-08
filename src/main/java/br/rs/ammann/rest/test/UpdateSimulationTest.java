package br.rs.ammann.rest.tasks;

import br.rs.ammann.rest.core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UpdateSimulationTest extends BaseTest {

    @Test
    public void updateSimulationTest() {
        given()
                .body("{\"email\": \"abcd@mann.com\", \"parcelas\": 16, \"seguro\": false}")
        .when()
                .put("simulacoes/66414919004")
        .then()
                .statusCode(200)
                .body("email", is("abcd@mann.com"))
                .body("parcelas", is(16))
                .body("seguro", is(false))
        ;
    }

    @Test
    public void verifyMandatoryDataInUpdateSimulationTest() {
        given()
                .body("{\"email\": \"\", \"parcelas\": 15, \"seguro\": true}")
        .when()
                .put("simulacoes/66414919004")
        .then()
                .statusCode(400)
                .body("erros.email", is("E-mail deve ser um e-mail válido"))
        ;
    }

    @Test
    public void updateNonexistentSimulationTest() {
        given()
                .body("{\"email\": \"abcefg@mann.com\", \"valor\": 9333, \"parcelas\": 11, \"seguro\": false}")
        .when()
                .put("simulacoes/66414919008")
        .then()
                .statusCode(404)
                .body("mensagem", is("CPF 66414919008 não encontrado"))
        ;
    }
}