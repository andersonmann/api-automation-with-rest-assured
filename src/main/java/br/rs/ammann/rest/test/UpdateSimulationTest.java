package br.rs.ammann.rest.test;

import br.rs.ammann.rest.core.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
/**
 * @author anderson.mann
 *
 */

public class UpdateSimulationTest extends BaseTest {

    @Test(priority = 0, description = "Altera os dados de uma simulação existente", groups = {"smoke.test"})
    public void updateSimulationTest() {
        given()
                .body("{\"email\": \"abcde@mann.com\", \"parcelas\": 12, \"seguro\": false}")
        .when()
                .put("simulacoes/66414919004")
        .then()
                .statusCode(200)
                .body("email", is("abcde@mann.com"))
                .body("parcelas", is(12))
                .body("seguro", is(false))
        ;
    }

    @Test(priority = 1, description = "Verifica os dados obrigatórios durante a alteração de uma simulação", groups = {"smoke.test"})
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

    @Test(priority = 2, description = "Tenta alterar uma simulação inexistente", groups = {"smoke.test"})
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