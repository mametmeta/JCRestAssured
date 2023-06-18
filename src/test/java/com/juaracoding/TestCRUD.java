package com.juaracoding;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseLogSpec;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TestCRUD {

    @Test
    public void testApiWithKeyAuth() {
        given()
                .queryParam("api_key", "f36ef38baad47752cd807916a3fbdbd8")
                .when()
                .get("https://api.themoviedb.org/3/movie/popular?language=en-US&page=2")
                .then().statusCode(200).log().all();
        System.out.println("Popular Get");
    }
    @Test
    public void  testGetMovie(){
        given()
                .queryParam("api_key","f36ef38baad47752cd807916a3fbdbd8")
                .when()
                .get("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
                .then().statusCode(200).log().all();
        System.out.println("Movie Get");
    }
    @Test
    public void addRating() {
        HashMap data = new HashMap();
        data.put("id", "1115710");
        data.put("value", "9");

        
        given()
                .contentType("application/json")
                .when()
                .post("https://api.themoviedb.org/3/tv/1115710/rating")

                .then()
                .statusCode(200)
                .log();
        String jsonString = null;
        Assert.assertEquals(jsonString.contains("The item/record was updated successfully"), true);
    }

    }


