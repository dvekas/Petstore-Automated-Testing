package org.dvekas.controller.REST;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RequestHandler {

    private static final Logger LOG = LogManager.getLogger(RequestHandler.class);

    /**
     * Sends a POST request, to the given URI,validates the response code, to the expected one then returns the Response.
     *
     * @param URI Address for the GET request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response sendGetRequest(String URI, int expectedStatusCode) {
        logRequest(Method.GET, URI, null);

        Response response =
                given()
                        .headers("Content-Type", "application/json")
                .when()
                        .get(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        LOG.info(response.getBody().prettyPeek());
        return response;
    }

    /**
     * Sends a POST request, to the given URI, with the given body, validates the response code, to the expected one then returns the Response.
     *
     * @param URI Address for the POST request.
     * @param requestBody The content of the request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @param contentType The contentType of the body, that will be sent as part of the request.
     * @return The Response object, from the request.
     */
    public Response sendPostRequest(String URI, String requestBody, int expectedStatusCode, ContentTypeEnum contentType) {
        logRequest(Method.POST, URI, requestBody);

        Response response =
                given()
                    .headers("Content-Type", contentType.getContentTypeString())
                    .body(requestBody)
                .when()
                    .post(URI)
                 .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        LOG.info(response.getBody().prettyPeek());
        return response;
    }

    /**
     * Sends a POST request, to the given URI, with the given file, validates the response code, to the expected one then returns the Response.
     *
     * @param URI Address for the POST request.
     * @param file The file to send.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response sendPostFileUploadRequest(String URI, File file, int expectedStatusCode, String additionalMetadata) {
        logRequest(Method.POST, URI, null);
        LOG.info("File to be POSTed: {}", file.getAbsolutePath());

        Response response =
                given()
                        .headers("Content-Type", "multipart/form-data")
                        .multiPart("file", file)
                        .multiPart("additionalMetadata", additionalMetadata)
                .when()
                        .post(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        LOG.info(response.getBody().prettyPeek());
        return response;
    }

    /**
     * Sends a PUT request, to the given URI, with the given body, validates the response code, to the expected one then returns the Response.
     *
     * @param URI Address for the POST request.
     * @param requestBody The content of the request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response sendPutRequest(String URI, String requestBody, int expectedStatusCode) {
        logRequest(Method.PUT, URI, requestBody);

        Response response =
                given()
                        .headers("Content-Type", "application/json")
                        .body(requestBody)
                .when()
                        .put(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        LOG.info(response.getBody().prettyPeek());
        return response;
    }

    /**
     * Sends a DELETE request, to the given URI, validates the response code, to the expected one then returns the Response.
     *
     * @param URI Address for the DELETE request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response sendDeleteRequest(String URI, int expectedStatusCode) {
        logRequest(Method.DELETE, URI, null);

        Response response =
                given()
                        .headers("api-key:", "special-key")
                .when()
                        .delete(URI)
                .then()
                        .statusCode(expectedStatusCode)
                        .extract().response();

        LOG.info(response.getBody().prettyPeek());
        return response;
    }

    /**
     * Logs information about the HTTP request to the console.
     *
     * @param requestType Type of the HTTP request.
     * @param URI URI, where the request is sent.
     * @param requestBody Body of the request. Can be 'null'.
     */
    private void logRequest(Method requestType,  String URI, @Nullable String requestBody) {
        LOG.info("Sending {} Request to URI: {}", requestType, URI);
        if (requestBody != null) {
            LOG.info("Request Body: {}", requestBody);
        }
    }

}
