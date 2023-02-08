package br.rs.ammann.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserJasonTest {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "https://restapi.wcaquino.me";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecification = responseSpecBuilder.build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }

    @Test
    public void deveVerificarPrimeiroNivel(){
        given()
        .when()
            .get("/users/1")
        .then()
            .body("id", is(1))
            .body("name", containsString("Silva"))
            .body("age", greaterThan(18))
        ;
    }

    @Test
    public void deveVerificarPrimeiroNivelOutrasFormas(){
        Response response = RestAssured.request(Method.GET, "/users/1");
        // path
        assertEquals(new Integer(1), response.path("id"));
        assertEquals(new Integer(1), response.path("%s", "id"));

        //jsonpath
        JsonPath jsonPath = new JsonPath(response.asString());
        assertEquals(1, jsonPath.getInt("id"));
    }

    @Test
    public void deveVerificarSegundoNivel(){
        given()
        .when()
            .get("/users/2")
        .then()
            .body("name", containsString("Joaquina"))
            .body("endereco.rua", is("Rua dos bobos"))
        ;
    }
    @Test
    public void deveVerificarLista(){
        given()
        .when()
            .get("/users/3")
        .then()
            .body("name", containsString("Ana"))
            .body("filhos", hasSize(2))
            .body("filhos[0].name", is("Zezinho"))
            .body("filhos[1].name", is("Luizinho"))
            .body("filhos.name", hasItem("Zezinho"))
            .body("filhos.name", hasItems("Zezinho", "Luizinho"))
        ;
    }

    @Test
    public void deveRetornarErroUsuarioInexistente(){
        given()
        .when()
            .get("/users/4")
        .then()
            .statusCode(404)
            .body("error", is("Usuário inexistente"))
        ;
    }

    @Test
    public void deveVerificarListaRaiz(){
        given()
        .when()
            .get("/users")
        .then()
            .body("$", hasSize(3))
            .body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
            .body("age[1]", is(25))
            .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
            .body("salary", contains(1234.5678f, 2500, null))
        ;
    }

    @Test
    public void devoFazerVerificacoesAvancadas(){
        given()
        .when()
            .get("/users")
        .then()
            .body("$", hasSize(3))
            .body("age.findAll{it <= 25}.size()", is(2))
            .body("age.findAll{it <= 25 && it > 20}.size()", is(1))
            .body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"))
            .body("findAll{it.age <= 25}[0].name", is("Maria Joaquina"))
            .body("findAll{it.age <= 25}[-1].name", is("Ana Júlia"))
            .body("find{it.age <= 25}.name", is("Maria Joaquina"))
            .body("findAll{it.name.contains('n')}.name", hasItems("Ana Júlia", "Maria Joaquina"))
            .body("findAll{it.name.length() > 10}.name", hasItems("João da Silva", "Maria Joaquina"))
            .body("name.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))
            .body("age.collect{it * 2}", hasItems(60, 50, 40))
            .body("id.max()", is(3))
            .body("salary.min()", is(1234.5678f))
            .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001)))
            .body("salary.findAll{it != null}.sum()", allOf(greaterThan(3000d), lessThan(4000d)))
        ;
    }
    @Test
    public void devoUnirJasonPathComJava(){
        ArrayList<String> names =
            given()
                    .when()
                    .get("/users")
                    .then()
                    .extract().path("name.findAll{it.startsWith('Maria')}")
            ;
        assertEquals(1, names.size());
        assertTrue(names.get(0).equalsIgnoreCase("mAria Joaquina"));
        assertEquals(names.get(0).toUpperCase(), "maria joaquina".toUpperCase());
    }
}