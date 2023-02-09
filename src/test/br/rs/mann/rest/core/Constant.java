package br.rs.ammann.rest.core;

import io.restassured.http.ContentType;

public interface Constant {
    String APP_BASE_URL = "http://localhost";
    Integer APP_PORT = 8080;
    public String APP_BASE_PATH = "api/v1/";
    ContentType APP_CONTENT_TYPE = ContentType.JSON;
    Long MAX_TIMEOUT = 10000L;

}
