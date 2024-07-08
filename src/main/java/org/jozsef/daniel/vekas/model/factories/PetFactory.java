package org.jozsef.daniel.vekas.model.factories;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;

import java.util.Random;

public class PetFactory {

    public static Pet getPet(String name, PetStatusEnum petStatus){
        return new Pet(generateRandomID(), name, petStatus);
    }

    private static int generateRandomID() {
        Random rnd = new Random();
        return 1000 + rnd.nextInt(90000);
    }
}
