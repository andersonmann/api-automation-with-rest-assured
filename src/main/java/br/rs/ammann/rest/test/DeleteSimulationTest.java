package br.rs.ammann.rest.test;

import br.rs.ammann.rest.core.BaseTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class DeleteSimulationTest extends BaseTest {
    @Test(priority = 1, description = "Deleta a simulação desejada", groups = {"smoke.test"})
    public void deleteSimulation(){
//        ArrayList<String> simulations =
//                given()
//                        .when()
//                        .get("simulacoes")
//                        .then()
//                        .statusCode(200)
//                        .extract().path("nome.findAll{it != null}")
//                ;
//        assertTrue(!simulations.isEmpty());
                ArrayList<String> simulations =
                given()
                .when()
                        .get("simulacoes")
                .then()
                        .statusCode(200)
                        .extract().path("id.findAll{it != null}")
                        ;
                given()
                        .when()
                .delete("simulacoes/13")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }
}
