package br.rs.ammann.rest;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AuthTest {

    @Test
    public void deveAcessarSWAPI() {
        given()
                .log().all()
                .when()
                .get("https://swapi.dev/api/people/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Luke Skywalker"))
        ;
    }

    @Test
    public void deveObterClima() {
        given()
                .log().all()
                .queryParam("q", "Porto Alegre, BR")
                .queryParam("appid", "bf60142468f98f95c0969b6390596999")
                .queryParam("units", "metric")
                .when()
                .get("https://api.openweathermap.org/data/2.5/weather")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Porto Alegre"))
        ;
    }

    @Test
    public void naoDeveAcessarSemSenha() {
        given()
                .log().all()
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(401)
        ;
    }

    @Test
    public void deveFazerAutenticacaoBasica() {
        given()
                .log().all()
                .when()
                .get("https://admin:senha@restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", is("logado"))
        ;
    }

    @Test
    public void deveFazerAutenticacaoBasica2() {
        given()
                .log().all()
                .auth().basic("admin", "senha")
                .when()
                .get("https://restapi.wcaquino.me/basicauth")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", is("logado"))
        ;
    }

    @Test
    public void deveFazerAutenticacaoBasicaComChallenge() {
        given()
                .log().all()
                .auth().preemptive().basic("admin", "senha")
                .when()
                .get("https://restapi.wcaquino.me/basicauth2")
                .then()
                .log().all()
                .statusCode(200)
                .body("status", is("logado"))
        ;
    }

    //
    @Test
    public void deveFazerAutenticacaoComTokenJWT() {
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "anderson@mann.com");
        login.put("senha", "123456");

        String token =
                given()
                        .log().all()
                        .body(login)
                        .contentType(ContentType.JSON)
                .when()
                        .post("http://barrigarest.wcaquino.me/signin")
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract().path("token");
        ;

        given()
                .log().all()
                .header("Authorization", "JWT" + token)
        .when()
                .get("http://barrigarest.wcaquino.me/contas")
        .then()
                .log().all()
                .statusCode(200)
        ;
    }


}
