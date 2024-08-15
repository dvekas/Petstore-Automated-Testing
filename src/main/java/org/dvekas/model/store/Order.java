package org.dvekas.model.store;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
