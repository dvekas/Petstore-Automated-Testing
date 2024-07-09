package org.jozsef.daniel.vekas.model.factories.pet;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;

public class PetBuilder {

    String petID;
    String name;
    PetStatusEnum petStatus;

    public Pet build() {
        return new Pet(petID, name, petStatus);
    }

    public PetBuilder petID(String petID) {
        this.petID = petID;
        return this;
    }

    public PetBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder petStatus(PetStatusEnum petStatus) {
        this.petStatus = petStatus;
        return this;
    }
}