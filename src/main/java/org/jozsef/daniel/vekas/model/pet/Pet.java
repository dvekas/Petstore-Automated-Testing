package org.jozsef.daniel.vekas.model.pet;

public class Pet {
    String id;
    String name;
    PetStatusEnum status;

    public Pet(String id, String name, PetStatusEnum status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public PetStatusEnum getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(PetStatusEnum status) {
        this.status = status;
    }
}
