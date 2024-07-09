package org.jozsef.daniel.vekas.controller;

import io.restassured.response.Response;
import org.jozsef.daniel.vekas.model.APIResponse;

public class ApiResponseMapper {

    /**
     * Creates an APIResponse object, from the body of the given Response object
     *
     * @param response Response object, with a APIResponse's data in the body, as JSON
     * @return The created APIResponse object
     */
    public APIResponse mapAPIResponseFromResponse(Response response) {
        int code;
        String type;
        String message;

        code = Integer.parseInt(getValueFromResponseBody(response, "code"));
        type = getValueFromResponseBody(response, "type");
        message = getValueFromResponseBody(response, "message");

        return new APIResponse(code, type, message);

    }

    private String getValueFromResponseBody(Response response, String key) {
        return response.getBody().jsonPath().getString(key);
    }
}
