package br.rs.ammann.rest.tasks;

import br.rs.ammann.rest.core.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateSimalationTest extends BaseTest {
    @Test
    public void createASimulationTest(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", "04054351000");
        simulation.put("nome", "Anderson Mann");
        simulation.put("email", "anderson@mann.com");
        simulation.put("valor", 1500);
        simulation.put("parcelas", 5);
        simulation.put("seguro", true);

        given()
            .body(simulation)
        .when()
            .post("simulacoes")
        .then()
            .statusCode(201)
            .body("id", is(notNullValue()))
            .body("cpf", is("04054351000"))
            .body("nome", is("Anderson Mann"))
            .body("email", is("anderson@mann.com"))
            .body("valor", is(1500))
            .body("parcelas", is(5))
            .body("seguro", is(true))
        ;
    }
    @Test
    public void validateExistingCpfTest(){
        given()
                .body("{\"cpf\": \"04054351000\", \"nome\": \"Anderson Mann\", \"email\": \"anderson@mann.com\", \"valor\": 1500, \"parcelas\": 5, \"seguro\": true}")
        .when()
                .post("simulacoes")
        .then()
                .statusCode(400)
                .body("mensagem", is("CPF duplicado"))
        ;
    }

    @Test
    public void verifyMandatoryDataTest(){
        given()
                .body("{\"cpf\": \"\", \"nome\": \"\", \"email\": \"\", \"valor\": null, \"null\": 5, \"seguro\": null}")
        .when()
                .post("simulacoes")
        .then()
                .statusCode(400)
                .body("erros.parcelas", is("Parcelas não pode ser vazio"))
                .body("erros.valor", is("Valor não pode ser vazio"))
                .body("erros.email", is("E-mail deve ser um e-mail válido"))
        ;
    }

    /**
     * This method was created to reproduce a bug founded.
     * Although the documentation does not address this scenario, It's possible create a new simulation with invalid CPF
     */
    @Test
    public void createAnSimulationWithInvalidCpfTest(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", "123456");
        simulation.put("nome", "");
        simulation.put("email", "a@a.com");
        simulation.put("valor", 1000);
        simulation.put("parcelas", 10);
        simulation.put("seguro", false);

        given()
                .body(simulation)
        .when()
                .post("simulacoes")
        .then()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("cpf", is("123456"))
                .body("nome", is(""))
                .body("email", is("a@a.com"))
                .body("valor", is(1000))
                .body("parcelas", is(10))
                .body("seguro", is(false))
        ;
    }
}