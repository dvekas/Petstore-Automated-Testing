package org.jozsef.daniel.vekas.model.factories;

import org.jozsef.daniel.vekas.model.store.Order;

public class OrderBuilder {

    String id;
    int petId;
    int quantity;
    String shipDate;
    String status;
    boolean complete;

    public Order build() {
        return new Order(id, petId, quantity, shipDate, status, complete);
    }

    public OrderBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public OrderBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder setShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder setComplete(boolean complete) {
        this.complete = complete;
        return this;
    }
}
