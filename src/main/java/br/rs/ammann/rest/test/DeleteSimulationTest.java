package br.rs.ammann.rest.tasks;

import br.rs.ammann.rest.core.BaseTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteSimulationTest extends BaseTest {
    @Test
    public void deleteSimulation(){
        given()
        .when()
                .delete("simulacoes/63")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }
}
