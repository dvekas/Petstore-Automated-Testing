package org.dvekas.controller.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.dvekas.controller.REST.RequestController;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.model.APIResponse;
import org.dvekas.model.store.Order;

import java.util.Map;

public class OrderRequests {
    static String BASE_URI = "https://petstore.swagger.io/v2/store/order/";
    RequestController requestController;
    CustomObjectMapper customObjectMapper;

    public OrderRequests() {
        requestController = new RequestController();
        customObjectMapper = new CustomObjectMapper();
    }

    /**
     * Requests and returns a map of pet status codes to quantities.
     *
     * @return A map of pet status codes to quantities.
     */
    @SuppressWarnings("unchecked")
    public Map<String,Integer> getInventoryList() {
        Map<String,Integer> result;
        ObjectMapper objectMapper = new ObjectMapper();

        String responseBody = requestController.getEntity("https://petstore.swagger.io/v2/store/inventory", HttpStatus.SC_OK, null).body().asPrettyString();

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
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + orderID, HttpStatus.SC_OK, null),
                Order.class
        );
    }

    /**
     * Requests an Order object from the API.
     *
     * @param orderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public APIResponse getNonExistentOrderByID(String orderID) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + orderID, HttpStatus.SC_NOT_FOUND, null),
                APIResponse.class
        );
    }

    /**
     * Sends the Order object, into the API caller methods, to create a new Order entity
     *
     * @param orderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public Order createNewOrder(Order orderToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_OK),
                Order.class
        );
    }

    /**
     * Sends the Order object, into the API caller methods, to unsuccessfully create a new Order entity
     *
     * @param orderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public APIResponse failToCreateOrder(Order orderToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_INTERNAL_SERVER_ERROR),
                APIResponse.class
        );
    }

    /**
     * Deletes an Order entity from the database, trough API call
     *
     * @param orderToDelete The Order to be deleted
     * @return API response object
     */
    public APIResponse deleteOrder(Order orderToDelete) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.deleteEntity(BASE_URI + orderToDelete.getId(), HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Deletes an Order entity from the database, trough API call and fails
     *
     * @param orderID Non existent OrderID
     */
    public APIResponse failToDeleteOrder(String orderID) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.deleteEntity(BASE_URI + orderID, HttpStatus.SC_NOT_FOUND),
                APIResponse.class
        );
    }

}