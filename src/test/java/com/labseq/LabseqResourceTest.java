package com.labseq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;

@QuarkusTest
public class LabseqResourceTest {

    @Test
    public void testBaseCase() {
        given().pathParam("n", 0)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(200)
                .body("input", equalTo(0))
                .body("value", equalTo(0));
    }

    @Test
    public void testSmallValues() {
        // Labseq(1) = 1
        given().pathParam("n", 1)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(200)
                .body("input", equalTo(1))
                .body("value", equalTo(1));

        // Labseq(2) = 0
        given().pathParam("n", 2)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(200)
                .body("input", equalTo(2))
                .body("value", equalTo(0));

        // Labseq(3) = 1
        given().pathParam("n", 3)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(200)
                .body("input", equalTo(3))
                .body("value", equalTo(1));
    }

    @Test
    public void testLargeValue() {
        given().pathParam("n", 100000)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    public void testNegativeValue() {
        given().pathParam("n", -1)
                .when().get("/labseq/{n}")
                .then()
                .statusCode(400);
    }

    @Test
    public void testLargeValueComputationIsFast() {
        assertTimeout(Duration.ofSeconds(10), () -> {
            given()
                    .when().get("/labseq/100000")
                    .then()
                    .statusCode(200)
                    .body("input", equalTo(100000))
                    .body("value", notNullValue());
        });
    }
}
