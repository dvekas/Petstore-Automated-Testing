package org.jozsef.daniel.vekas.model.pet;

public class Pet {
    int id;
    String name;
    PetStatusEnum status;

    public Pet(String name, PetStatusEnum status) {
        this.name = name;
        this.status = status;
    }

    public Pet(int id, String name, PetStatusEnum status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PetStatusEnum getStatus() {
        return status;
    }
}
