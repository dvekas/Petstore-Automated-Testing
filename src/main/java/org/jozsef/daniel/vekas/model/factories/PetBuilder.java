package org.jozsef.daniel.vekas.model.factories;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;

public class PetBuilder {

    Integer petID;
    String name;
    PetStatusEnum petStatus;

    public Pet build() {
        if (petID != null) {
            return new Pet(petID, name, petStatus);
        }
        return new Pet(name, petStatus);
    }

    public PetBuilder petID(int petID) {
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
