package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author anderson.mann
 */

public class ConsultSimulationTest extends BaseTest {

    @Test(description = "Realiza a consulta de uma simulação")
    public void consultSimulationTest() {
        given()
                .when()
                .get("simulacoes/66414919004")
                .then()
                .statusCode(200)
                .body("nome", is("Fulano"))
                .body("cpf", is("66414919004"))
        ;
    }

    @Test(description = "Consulta um CPF inexistente")
    public void consultCpfWithOutSimulationTest() {
        given()
        .when()
                .get("simulacoes/66414919005")
        .then()
                .statusCode(404)
                .body("mensagem", is("CPF 66414919005 não encontrado"))
        ;
    }

    @Test(description = "Recebe uma lista de simulações e verifica a existência de um nome específico")
    public void consultSimulationByName() {
        ArrayList<String> names =
                given()
                .when()
                        .get("simulacoes")
                .then()
                        .statusCode(200)
                        .extract().path("nome.findAll{it.startsWith('Deltrano')}");
        assertEquals(1, names.size());
        assertEquals("Deltrano", names.get(0));
    }

    @Test(description = "Verifica se existe alguma simulação cadastrada")
    public void verifyIfListOfSimulationIsEmpity() {
        ArrayList<String> simulations =
                given()
                .when()
                        .get("simulacoes")
                .then()
                        .statusCode(200)
                        .extract().path("nome.findAll{it != null}");
        assertTrue(!simulations.isEmpty());
    }
}