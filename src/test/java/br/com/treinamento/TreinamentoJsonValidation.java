package br.com.treinamento;

import br.com.treinamento.bin.People;
import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.RestAssured.given;
import com.google.gson.Gson;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TreinamentoJsonValidation {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://swapi.co/api";
    }

    @After
    public void tearDown() {
        System.out.println("Terminou");
    }

    @Test
    public void schemaValidatorWithAs() {
        given()
        .when()
            .get("people/1")
        .then()
            .log().all()
            .extract().as(People.class, ObjectMapperType.GSON);
    }

    @Test
    public void schemaValidatorWithJsonSchemaValidator() {
        given()
        .when()
            .get("people/1")
        .then()
            .assertThat().body(matchesJsonSchemaInClasspath("people1.json"));
    }
}
