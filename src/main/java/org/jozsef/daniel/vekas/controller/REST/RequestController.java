package org.jozsef.daniel.vekas.controller.REST;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class RequestController {

    /**
     * Returns the response from a GET request
     *
     * @param URL Address for the post request
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public Response getEntity(String URL, int expectedStatusCode) {
        return new RequestHandler().sendGetRequest(URL, expectedStatusCode);
    }

    /**
     * Creates a new entity, by using a post API call, after mapping the given object into JSON
     *
     * @param URL Address for the post request
     * @param Object The new entity to be created (will be mapped into JSON)
     * @param expectedStatusCode The status code, that should be returned from the API
     * @return The Response object, from the request
     */
    public <T> Response createNewEntity(String URL, T Object, int expectedStatusCode) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String JsonBody;

        try {
            JsonBody = mapper.writeValueAsString(Object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new RequestHandler().sendPostRequest(URL, JsonBody, expectedStatusCode);
    }
}
