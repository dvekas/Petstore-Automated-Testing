package org.dvekas.controller.REST;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.File;

public class RequestController {

    /**
     * Returns the response from a GET request.
     *
     * @param URL Address for the GET request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response getEntity(String URL, int expectedStatusCode) {
        return new RequestHandler().sendGetRequest(URL, expectedStatusCode);
    }

    /**
     * Creates a new entity, by using a POST call, after mapping the given object into JSON.
     *
     * @param URL Address for the POST request.
     * @param Object The new entity to be created (will be mapped into JSON).
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public <T> Response createNewEntity(String URL, T Object, int expectedStatusCode) {
        return new RequestHandler().sendPostRequest(URL, mapObjectToJSON(Object), expectedStatusCode, ContentTypeEnum.JSON);
    }

    /**
     * Uploads a new file, by using a POST call to the API.
     *
     * @param URL Address for the POST request.
     * @param file The file to be uploaded.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response uploadFile(String URL, File file, int expectedStatusCode, String additionalMetadata) {
        return new RequestHandler().sendPostFileUploadRequest(URL, file, expectedStatusCode, additionalMetadata);
    }

    /**
     * Creates or updates an entity, by using a PUT call, after mapping the given object into JSON.
     *
     * @param URL Address for the PUT request.
     * @param Object The new entity to be created or updated (will be mapped into JSON).
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public <T> Response createOrUpdateEntity(String URL, T Object, int expectedStatusCode) {
        return new RequestHandler().sendPutRequest(URL, mapObjectToJSON(Object), expectedStatusCode);
    }

    /**
     * Updates and entity, by using a POST call and FormData format.
     *
     * @param URL Address for the POST request.
     * @param formData Data, for updating, in FormData format.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response updateEntityViaPostRequest(String URL, String formData, int expectedStatusCode) {
        return new RequestHandler().sendPostRequest(URL, formData, expectedStatusCode, ContentTypeEnum.FORM_DATA);
    }

    /**
     * Returns the response from a DELETE request.
     *
     * @param URL Address for the DELETE request.
     * @param expectedStatusCode The status code, that should be returned from the API.
     * @return The Response object, from the request.
     */
    public Response deleteEntity(String URL, int expectedStatusCode) {
        return new RequestHandler().sendDeleteRequest(URL, expectedStatusCode);
    }

    /**
     * Translates the given object to JSON format.
     * @param Object The Object, to be formatted into JSON.
     * @return The JSON object.
     */
    private <T> String mapObjectToJSON(T Object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            return mapper.writeValueAsString(Object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
