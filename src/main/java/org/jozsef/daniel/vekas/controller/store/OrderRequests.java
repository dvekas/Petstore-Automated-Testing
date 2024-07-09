package org.jozsef.daniel.vekas.controller.store;

import org.apache.http.HttpStatus;
import org.jozsef.daniel.vekas.controller.ApiResponseMapper;
import org.jozsef.daniel.vekas.controller.REST.RequestController;
import org.jozsef.daniel.vekas.model.APIRespone;
import org.jozsef.daniel.vekas.model.store.Order;

public class OrderRequests {
    static String BASE_URI = "https://petstore.swagger.io/v2/store/order/";
    RequestController requestController;

    /**
     * Requests an Order object from the API.
     *
     * @param orderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public Order getOrderByID(String orderID) {
        requestController = new RequestController();

        return new OrderMapper().mapOrderFromResponse(requestController.getEntity(BASE_URI + orderID, HttpStatus.SC_OK));
    }

    /**
     * Requests an Order object from the API.
     *
     * @param orderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public APIRespone getNonExistentOrderByID(String orderID) {
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

        return new OrderMapper().mapOrderFromResponse(requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_OK));
    }

    /**
     * Sends the Order object, into the API caller methods, to unsuccessfully create a new Order entity
     *
     * @param orderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public APIRespone failToCreateOrder(Order orderToCreate) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.createNewEntity(BASE_URI, orderToCreate, HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

    /**
     * Deletes an Order entity from the database, trough API call
     *
     * @param orderToDelete The Order to be deleted
     * @return API response object
     */
    public APIRespone deleteOrder(Order orderToDelete) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.deleteEntity(BASE_URI + orderToDelete.getId(), HttpStatus.SC_OK));
    }

    /**
     * Deletes an Order entity from the database, trough API call and fails
     *
     * @param orderID Non existent OrderID
     */
    public void failTpDeleteOrder(String orderID) {
        requestController = new RequestController();

        requestController.deleteEntity(BASE_URI + orderID, HttpStatus.SC_NOT_FOUND);
    }
}
