package org.jozsef.daniel.vekas.controller.factories;
import org.jozsef.daniel.vekas.model.Pet.Pet;
import org.jozsef.daniel.vekas.model.Pet.PetStatus;

import java.util.Random;

public class PetFactory {

    public static Pet getPet(String name, PetStatus petStatus){
        return new Pet(generateRandomID(), name, petStatus);
    }

    private static int generateRandomID() {
        Random rnd = new Random();
        return 1000 + rnd.nextInt(90000);
    }
}
