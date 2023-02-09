package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @author anderson.mann
 */

public class DeleteSimulationTest extends BaseTest {

    @Test(description = "Deleta a simulacao desejada")
    public void deleteSimulation() {
        given()
        .when()
                .delete("simulacoes/12")
        .then()
                .statusCode(200)
        ;
    }
}