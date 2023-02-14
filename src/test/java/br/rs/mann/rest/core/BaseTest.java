package br.rs.mann.rest.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

import static org.hamcrest.Matchers.lessThan;

/**
 * @author anderson.mann
 */

public class BaseTest implements Constant {

    private static Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

    @BeforeClass(description = "Criando configurações padrões")
    public static void setup() {
        RestAssured.baseURI = APP_BASE_URL;
        LOGGER.info("HOST utilizado: ".concat((APP_BASE_URL)));

        RestAssured.port = APP_PORT;
        LOGGER.info("Porta padrão? ".concat(String.valueOf(APP_PORT)));

        RestAssured.basePath = APP_BASE_PATH;
        LOGGER.info("Base path utilizado: ".concat((APP_BASE_PATH)));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        LOGGER.info("Formato de arquivo  utilizado: ".concat(String.valueOf(APP_CONTENT_TYPE)));

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));
        LOGGER.info("Tempo máximo de espera em milisegundos: ".concat(String.valueOf(MAX_TIMEOUT)));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        LOGGER.config("Configuração concluída.");
    }

    static {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/test/resources/logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.setLevel(Level.FINE);
        LOGGER.addHandler(new ConsoleHandler());
    }
}