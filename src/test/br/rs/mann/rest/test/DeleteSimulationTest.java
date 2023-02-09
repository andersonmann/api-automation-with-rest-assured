package br.rs.ammann.rest.test;

import br.rs.ammann.rest.core.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * @author anderson.mann
 */

public class DeleteSimulationTest extends BaseTest {

    @Test(priority = 0, description = "Deleta a simulação desejada", groups = {"smoke.test"})
    public void deleteSimulation() {
        given()
        .when()
                .delete("simulacoes/12")
        .then()
                .statusCode(200)
        ;
    }
}