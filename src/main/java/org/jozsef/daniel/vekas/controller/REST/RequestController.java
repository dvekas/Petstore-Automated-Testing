package org.jozsef.daniel.vekas.controller.REST;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class RequestController {

    public <T> Response createNewPet(String URL, T Object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String JsonBody;

        try {
            JsonBody = mapper.writeValueAsString(Object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new RequestHandler().sendPostRequest(URL, JsonBody);
    }
}
