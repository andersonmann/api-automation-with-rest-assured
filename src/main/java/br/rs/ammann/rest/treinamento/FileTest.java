package br.rs.ammann.rest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class FileTest {
    @Test
    public void deveObrigarEnvioArquivo() {
        given()
            .log().all()
        .when()
                .post("https://restapi.wcaquino.me/upload")
        .then()
            .log().all()
            .statusCode(404)
            .body("error", is("Arquivo não enviado"))
        ;
    }
    @Test
    public void deveFazerUploadArquivo() {
        given()
            .log().all()
            .multiPart("'arquivo", new File("src/main/resources/users.pdf"))
        .when()
            .post("http://restapi.wcaquino.me/upload")
        .then()
            .log().all()
            .statusCode(200)
            .body("name", is("users.pdf"))
        ;
    }
    @Test
    public void naoDeveFazerUploadArquivoGrande() {
        given()
            .log().all()
            .multiPart("'arquivo", new File("src/main/resources/arquivoGrande.pdf"))
        .when()
            .post("http://restapi.wcaquino.me/upload")
        .then()
            .log().all()
            .time(lessThan(1000L))
            .statusCode(413)
        ;
    }

    @Test
    public void deveBaixarArquivo() throws IOException {
//        byte[] image = given()
//                .log().all()
//            .when()
//                .get("http://restapi.wcaquino.me/download")
//            .then()
//                .statusCode(200)
//                .extract().asByteArray()
        ;
//        File imagem = new File("src/main/resources/file.jpg");
//        OutputStream outputStream = new FilterOutputStream(imagem);
//        outputStream.write(image);
//        outputStream.close();
    }


}
