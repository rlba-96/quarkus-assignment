package com.labseq;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class LabseqResourceTest {

    @Test
    public void testBaseCase() {
        given().when().get("/labseq/0").then().statusCode(200).body(is("0"));
    }

    @Test
    public void testSmallValues() {
        given().when().get("/labseq/1").then().statusCode(200).body(equalTo("1"));
        given().when().get("/labseq/2").then().statusCode(200).body(equalTo("0"));
        given().when().get("/labseq/3").then().statusCode(200).body(equalTo("1"));
        given().when().get("/labseq/4").then().statusCode(200).body(equalTo("1"));
        given().when().get("/labseq/5").then().statusCode(200).body(equalTo("1"));
        given().when().get("/labseq/6").then().statusCode(200).body(equalTo("1"));
        given().when().get("/labseq/7").then().statusCode(200).body(equalTo("2"));
    }

    @Test
    public void testLargeValue() {
        given().when().get("/labseq/100000").then().statusCode(200).body(notNullValue());
    }

    @Test
    public void testNegativeValue() {
        given().when().get("/labseq/-1").then().statusCode(400);
    }
}
