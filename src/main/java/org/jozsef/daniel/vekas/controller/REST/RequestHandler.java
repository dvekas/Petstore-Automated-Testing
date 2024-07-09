package org.jozsef.daniel.vekas.controller.REST;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestHandler {

    /**
     * Sends a POST request, to the given URI,validates the response code, to the expected one then returns the Response
     *
     * @param URI Address for the GET request
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public Response sendGetRequest(String URI, int expectedStatusCode) {
        System.out.println("Sending GET Request to URI: " + URI);

        Response response =
                given()
                        .headers("Content-Type", "application/json")
                .when()
                        .get(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        System.out.println("Endpoint response: " + response.statusLine());
        System.out.println(response.getBody().prettyPrint());
        return response;
    }

    /**
     * Sends a POST request, to the given URI, with the given body, validates the response code, to the expected one then returns the Response
     *
     * @param URI Address for the POST request
     * @param requestBody The content of the request
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public Response sendPostRequest(String URI, String requestBody, int expectedStatusCode) {
        System.out.println("Sending POST Request to URI: " + URI);
        System.out.println("Request Body: " + requestBody);

        Response response =
                given()
                    .headers("Content-Type", "application/json")
                    .body(requestBody)
                .when()
                    .post(URI)
                 .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        System.out.println("Endpoint response: " + response.statusLine());
        System.out.println(response.getBody().prettyPrint());
        return response;
    }

    /**
     * Sends a PUT request, to the given URI, with the given body, validates the response code, to the expected one then returns the Response
     *
     * @param URI Address for the POST request
     * @param requestBody The content of the request
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public Response sendPutRequest(String URI, String requestBody, int expectedStatusCode) {
        System.out.println("Sending PUT Request to URI: " + URI);
        System.out.println("Request Body: " + requestBody);

        Response response =
                given()
                        .headers("Content-Type", "application/json")
                        .body(requestBody)
                .when()
                        .put(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        System.out.println("Endpoint response: " + response.statusLine());
        System.out.println(response.getBody().prettyPrint());
        return response;
    }

    /**
     * Sends a DELETE request, to the given URI, validates the response code, to the expected one then returns the Response
     *
     * @param URI Address for the DELETE request
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public Response sendDeleteRequest(String URI, int expectedStatusCode) {
        System.out.println("Sending DELETE Request to URI: " + URI);

        Response response =
                given()
                        .headers("api-key:", "special-key")
                .when()
                        .delete(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        System.out.println("Endpoint response: " + response.statusLine());
        System.out.println(response.getBody().prettyPrint());
        return response;
    }
}
