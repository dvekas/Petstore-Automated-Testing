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
     * @param OrderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public Order getOrderByID(String OrderID) {
        requestController = new RequestController();

        return new OrderMapper().mapOrderFromResponse(requestController.getEntity(BASE_URI + OrderID, HttpStatus.SC_OK));
    }

    /**
     * Requests an Order object from the API.
     *
     * @param OrderID ID of the Order object, we request
     * @return The requested Order, from the response body of the API call
     */
    public APIRespone getNonExistentOrderByID(String OrderID) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.getEntity(BASE_URI + OrderID, HttpStatus.SC_NOT_FOUND));
    }

    /**
     * Sends the Order object, into the API caller methods, to create a new Order entity
     *
     * @param OrderToCreate The Order to be created
     * @return The created Order, from the response body of the API call
     */
    public Order createNewOrder(Order OrderToCreate) {
        requestController = new RequestController();

        return new OrderMapper().mapOrderFromResponse(requestController.createNewEntity(BASE_URI, OrderToCreate, HttpStatus.SC_OK));
    }
}
