package org.jozsef.daniel.vekas.controller.Pet;
import org.jozsef.daniel.vekas.controller.REST.RequestController;
import org.jozsef.daniel.vekas.model.pet.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PetRequests {

    static String BASE_URI = "https://petstore.swagger.io/v2/pet";
    RequestController requestController = new RequestController();

    public Pet createNewPet(Pet petToCreate) {

        return new PetMapper().mapPetFromResponse(requestController.createNewPet(BASE_URI, petToCreate));
    }
}
