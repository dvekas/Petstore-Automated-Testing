package org.jozsef.daniel.vekas.controller.Pet;
import org.apache.http.HttpStatus;
import org.jozsef.daniel.vekas.controller.REST.RequestController;
import org.jozsef.daniel.vekas.model.pet.Pet;

public class PetRequests {

    static String BASE_URI = "https://petstore.swagger.io/v2/pet/";
    RequestController requestController;

    /**
     * Requests a Pet object from the API.
     *
     * @param petID ID of the Pet object, we request
     * @return The requested Pet, from the response body of the API call
     */
    public Pet getPetByID(int petID) {
        requestController = new RequestController();

        return new PetMapper().mapPetFromResponse(requestController.getEntity(BASE_URI + petID, HttpStatus.SC_OK));
    }

    /**
     * Sends the Pet object, into the API caller methods, to create a new Pet entity
     *
     * @param petToCreate The Pet to be created
     * @return The created Pet, from the response body of the API call
     */
    public Pet createNewPet(Pet petToCreate) {
        requestController = new RequestController();

        return new PetMapper().mapPetFromResponse(requestController.createNewEntity(BASE_URI, petToCreate, HttpStatus.SC_OK));
    }
}
