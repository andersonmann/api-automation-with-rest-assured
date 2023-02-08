package br.rs.ammann.rest;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OlaMundoTest {

    @Test
    public void testeOlaMundo() {
        Response response = request(Method.GET, "http://restapi.wcaquino.me:80/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        assertEquals(200, response.statusCode());
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured() {
        Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        get("http://restapi.wcaquino.me/ola").then().statusCode(200);

        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200);
    }

    @Test
    public void devoConhecerOsMatchersHamcrest(){
        Assert.assertThat("Maria" , Matchers.is("Maria"));
        Assert.assertThat(128 , Matchers.is(128));
        Assert.assertThat(128 , Matchers.isA(Integer.class));
        Assert.assertThat(128d , Matchers.isA(Double.class));
        Assert.assertThat(128d , Matchers.greaterThan(120d));
        Assert.assertThat(128d , Matchers.lessThan(130d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        assertThat(impares, Matchers.hasSize(5));
        assertThat(impares, contains(1,3,5,7,9));
        assertThat(impares, containsInAnyOrder(1,3,5,9,7));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1,5));

        assertThat("Maria", not("João"));
        assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
        assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
    }

    @Test
    public void devoValidarBody(){
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(is("Ola Mundo!"))
            .body(containsString("Mundo"))
            .body(is(not(nullValue())));
    }
}