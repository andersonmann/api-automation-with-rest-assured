package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
/**
 * @author anderson.mann
 *
 */

public class UpdateSimulationTest extends BaseTest {

    @Test(description = "Altera os dados de uma simulacao existente")
    public void updateSimulationTest() {
        given()
                .body("{\"email\": \"abcde@mann.com\", \"parcelas\": 9, \"seguro\": false}")
        .when()
                .put("simulacoes/66414919004")
        .then()
                .statusCode(200)
                .body("email", is("abcde@mann.com"))
                .body("parcelas", is(9))
                .body("seguro", is(false))
        ;
    }

    @Test(description = "Verifica os dados obrigatorios durante a alteracao de uma simulacao")
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

    @Test(priority = 2, description = "Tenta alterar uma simulacao inexistente", groups = {"smoke.test"})
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