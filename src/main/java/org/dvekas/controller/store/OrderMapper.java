package org.dvekas.controller.store;

import io.restassured.response.Response;
import org.dvekas.model.factories.store.OrderBuilder;
import org.dvekas.model.store.Order;

public class OrderMapper {

    /**
     * Creates an Order object, from the body of the given Response object
     *
     * @param response Response object, with a pet's data in the body, as JSON
     * @return The created Order object
     */
    public Order mapOrderFromResponse(Response response) {
        String id;
        int petId;
        int quantity;
        String shipDate;
        String status;
        boolean complete;

        id = getValueFromResponseBody(response, "id");
        petId = Integer.parseInt(getValueFromResponseBody(response, "petId"));
        quantity = Integer.parseInt(getValueFromResponseBody(response, "quantity"));
        shipDate = getValueFromResponseBody(response, "shipDate");
        status = getValueFromResponseBody(response, "status");
        complete = Boolean.getBoolean(getValueFromResponseBody(response, "complete"));

        return new OrderBuilder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(shipDate)
                .status(status)
                .complete(complete)
                .build();

    }

    private String getValueFromResponseBody(Response response, String key) {
        return response.getBody().jsonPath().getString(key);
    }
}
