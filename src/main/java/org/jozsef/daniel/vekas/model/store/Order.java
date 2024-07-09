package org.jozsef.daniel.vekas.model.store;

public class Order {
    String id;
    int petId;
    int quantity;
    String shipDate;
    String status;
    boolean complete;

    public Order(String id, int petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
}
