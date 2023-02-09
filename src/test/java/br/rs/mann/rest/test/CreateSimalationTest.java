package br.rs.mann.rest.test;

import br.rs.mann.rest.core.BaseTest;
import br.rs.mann.rest.util.DataGenerator;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
/**
 * @author anderson.mann
 *
 */

public class CreateSimalationTest extends BaseTest {
    private static final String RESOURCE = "simulacoes";
    private static final Boolean INSURANCE = true;
    DataGenerator dataGenerator = new DataGenerator();
    String cpf = dataGenerator.createCpf();
    String name = dataGenerator.createUserName();
    String email = dataGenerator.createEmail();

    @Test(description = "Cria uma nova simulacao")
    public void createSimulationTest(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", cpf);
        simulation.put("nome", name);
        simulation.put("email", email);
        simulation.put("valor", 1500);
        simulation.put("parcelas", 5);
        simulation.put("seguro", INSURANCE);

        given()
                .body(simulation)
        .when()
                .post(RESOURCE)
        .then()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .body("cpf", is(String.valueOf(cpf)))
                .body("cpf", is(String.valueOf(cpf)))
                .body("nome", is(String.valueOf(name)))
                .body("email", is(String.valueOf(email)))
                .body("valor", is(1500))
                .body("parcelas", is(5))
                .body("seguro", is(INSURANCE))
        ;
    }

    @Test(description = "Tenta criar uma nova simulacao com CPF existente")
    public void validateExistingCpfTest(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", cpf);
        simulation.put("nome", name);
        simulation.put("email", email);
        simulation.put("valor", 3000);
        simulation.put("parcelas", 6);
        simulation.put("seguro", INSURANCE);
        given()
                .body(simulation)
        .when()
                .post(RESOURCE)
        .then()
                .statusCode(400)
                .body("mensagem", is("CPF duplicado"))
        ;
    }

    @Test(description = "Verifica os dados obrigatorios na criacao de uma nova simulacao")
    public void validateMandatoryDataTest(){
        given()
                .body("{\"cpf\": \"\", \"nome\": \"\", \"email\": \"\", \"valor\": null, \"null\": 5, \"seguro\": null}")
        .when()
                .post(RESOURCE)
        .then()
                .statusCode(400)
                .body("erros.parcelas", is("Parcelas não pode ser vazio"))
                .body("erros.valor", is("Valor não pode ser vazio"))
                .body("erros.email", is("E-mail deve ser um e-mail válido"))
        ;
    }

    @Test(description = "Tenta criar uma nova simulacao com valor maior que R$40.000")
    public void validateMaximumValueTest(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", cpf);
        simulation.put("nome", name);
        simulation.put("email", email);
        simulation.put("valor", 50000);
        simulation.put("parcelas", 5);
        simulation.put("seguro", true);

        given()
                .body(simulation)
        .when()
                .post(RESOURCE)
        .then()
                .statusCode(400)
                .body("erros.valor", is("Valor deve ser menor ou igual a R$ 40.000"))
       ;
    }
    @Test(description = "Tenta criar uma nova simulacao com numero de parcelas menor que 2")
    public void validateMinimumNumberInstallments(){
        Map<String, Object> simulation =  new HashMap<>();
        simulation.put("cpf", cpf);
        simulation.put("nome", name);
        simulation.put("email", email);
        simulation.put("valor", 20000);
        simulation.put("parcelas", 1);
        simulation.put("seguro", INSURANCE);
        given()
                .body(simulation)
        .when()
                .post(RESOURCE)
        .then()
                .statusCode(400)
                .body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"))
        ;
    }
}