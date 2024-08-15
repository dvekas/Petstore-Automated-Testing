package org.dvekas.model.factories.store;

import org.dvekas.model.store.Order;

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

    public OrderBuilder id(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder petId(int petId) {
        this.petId = petId;
        return this;
    }

    public OrderBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder shipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder status(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder complete(boolean complete) {
        this.complete = complete;
        return this;
    }
}
