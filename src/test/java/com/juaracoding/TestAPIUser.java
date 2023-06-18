package com.juaracoding;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


public class TestAPIUser {
    String endpoint = "https://reqres.in/api/users?page=1";
    @Test
    public void testStatusCode() {
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        int actual = response.getStatusCode();
        Assert.assertEquals(actual, 200);
    }
    @Test
    public void testIdOne() {
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(1));
    }
    @Test
    public void testIdTwo() {
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("data.id[1]", equalTo(2));
    }
}

class TestScrapLocator {
    @Test
    public void testMessageBody() {
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.queryParam("page",2).get("/users");
        ResponseBody body = response.getBody();

        System.out.println("Response Body is: " + body.asString());
        JsonPath jsonPathEvaluator = response.jsonPath();
        String page = jsonPathEvaluator.getString("page");
        System.out.println(page);
    }
    @Test
    public void testLoginSuccessful() {
        RestAssured.baseURI = " https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();

        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "cityslicka");
        request.body(requestBody.toJSONString());
        Response response = request.post("/login");
        Assert.assertEquals(response.getStatusCode(), 200);
        String token = response.getBody().jsonPath().getString("token");
        System.out.println(token);
    }
}

