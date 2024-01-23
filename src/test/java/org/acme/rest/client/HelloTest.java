package org.acme.rest.client;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
@QuarkusTestResource(HelloTestResource.class)
public class HelloTest {

    @Test
    void hello() {
        RestAssured.given()
        .get("/client/hello")
        .then()
        .statusCode(200)
        .body(is("HelloWorld"));
    }
}
