package br.rs.ammann.rest.tasks;

import br.rs.ammann.rest.core.BaseTest;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsultSimulationTest extends BaseTest {
    @Test
    public void consultSimulationTest(){
        given()
        .when()
                .get("simulacoes/66414919004")
        .then()
                .statusCode(200)
                .body("nome", is("Fulano"))
                .body("cpf", is("66414919004"))
        ;
    }

    @Test
    public void consultCpfWithOutSimulationTest(){
        given()
        .when()
                .get("simulacoes/66414919005")
        .then()
                .statusCode(404)
                .body("mensagem", is("CPF 66414919005 não encontrado"))
        ;
    }
}