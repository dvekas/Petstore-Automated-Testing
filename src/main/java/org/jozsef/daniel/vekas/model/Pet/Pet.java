package org.jozsef.daniel.vekas.model.Pet;

public class Pet {
    int id;
    String name;
    PetStatus status;

    public Pet(int id, String name, PetStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
