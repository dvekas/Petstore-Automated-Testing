package org.dvekas.controller.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.dvekas.controller.REST.RequestController;
import org.dvekas.controller.ApiResponseMapper;
import org.dvekas.model.APIResponse;
import org.dvekas.model.store.Order;

import java.io.IOException;
import java.util.Map;

public class OrderRequests {
    static String BASE_URI = "https://petstore.swagger.io/v2/store/order/";
    RequestController requestController;

    /**
     * Requests and returns a map of pet status codes to quantities.
     *
     * @return A map of pet status codes to quantities.
     */
    @SuppressWarnings("unchecked")
    public Map<String,Integer> getInventoryList() {

        Map<String,Integer> result;
        ObjectMapper objectMapper = new ObjectMapper();
        requestController = new RequestController();

        String responseBody = requestController.getEntity("https://petstore.swagger.io/v2/store/inventory", HttpStatus.SC_OK).body().asPrettyString();

        try {
            result = objectMapper.readValue(responseBody, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Requests an Order object from the API.
     *
     * @param orderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public Order getOrderByID(String orderID) {
        requestController = new RequestController();

        return mapOrderFromResponse(requestController.getEntity(BASE_URI + orderID, HttpStatus.SC_OK));
    }

    /**
     * Requests an Order object from the API.
     *
     * @param orderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public APIResponse getNonExistentOrderByID(String orderID) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.getEntity(BASE_URI + orderID, HttpStatus.SC_NOT_FOUND));
    }

    /**
     * Sends the Order object, into the API caller methods, to create a new Order entity
     *
     * @param orderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public Order createNewOrder(Order orderToCreate) {
        requestController = new RequestController();

        return mapOrderFromResponse(requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_OK));
    }

    /**
     * Sends the Order object, into the API caller methods, to unsuccessfully create a new Order entity
     *
     * @param orderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public APIResponse failToCreateOrder(Order orderToCreate) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

    /**
     * Deletes an Order entity from the database, trough API call
     *
     * @param orderToDelete The Order to be deleted
     * @return API response object
     */
    public APIResponse deleteOrder(Order orderToDelete) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.deleteEntity(BASE_URI + orderToDelete.getId(), HttpStatus.SC_OK));
    }

    /**
     * Deletes an Order entity from the database, trough API call and fails
     *
     * @param orderID Non existent OrderID
     */
    public APIResponse failToDeleteOrder(String orderID) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.deleteEntity(BASE_URI + orderID, HttpStatus.SC_NOT_FOUND));
    }

    /**
     * Creates an Order object, from the body of the given Response object.
     *
     * @param response Response object, with a pet's data in the body, as JSON.
     * @return The created Order object.
     */
    private Order mapOrderFromResponse(Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order;

        try {
            order = objectMapper.readValue(response.getBody().asPrettyString(), Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return order;
    }

}
