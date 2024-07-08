package org.jozsef.daniel.vekas.controller.Pet;
import org.jozsef.daniel.vekas.controller.REST.RequestController;
import org.jozsef.daniel.vekas.model.Pet.Pet;

public class PetRequests {

    static String BASE_URI = "https://petstore.swagger.io/v2/pet";
    RequestController requestController = new RequestController();

    public void createNewPet(Pet petToCreate) {
        requestController.createNewPet(BASE_URI, petToCreate);

    }
}
