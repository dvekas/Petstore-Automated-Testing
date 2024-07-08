package org.jozsef.daniel.vekas.controller.REST;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.logging.Logger;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RequestHandler {

    public Response sendGetRequest(String URI) {
        return
                given()
                        .headers("Content-Type", "application/json")
                .when()
                        .get(URI);
    }

    public Response sendPostRequest(String URI, String requestBody) {
        RequestSpecification httpRequest = RestAssured.given()
                                                        .headers("Content-Type", "application/json")
                                                        .body(requestBody);

        System.out.println("Sending POST Request to URI: " + URI);
        System.out.println("Request Body: " + requestBody);
        Response response = httpRequest.request(Method.POST, URI);
        System.out.println(response.statusLine());
        return response;
    }
}
