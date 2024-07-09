package org.jozsef.daniel.vekas.controller.Pet;
import org.apache.http.HttpStatus;
import org.jozsef.daniel.vekas.controller.REST.RequestController;
import org.jozsef.daniel.vekas.model.APIRespone;
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
    public Pet getPetByID(String petID) {
        requestController = new RequestController();

        return new PetMapper().mapPetFromResponse(requestController.getEntity(BASE_URI + petID, HttpStatus.SC_OK));
    }

    /**
     * Requests a Pet object from the API.
     *
     * @param petID ID of the Pet object, we request
     * @return The requested Pet, from the response body of the API call
     */
    public APIRespone getNonExistentPetByID(String petID) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.getEntity(BASE_URI + petID, HttpStatus.SC_NOT_FOUND));
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

    /**
     * Sends the Pet object, into the API caller methods, to unsuccessfully create a new Pet entity
     *
     * @param petToCreate The Pet to be created
     * @return The created Pet, from the response body of the API call
     */
    public APIRespone failToCreatePet(Pet petToCreate) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.createNewEntity(BASE_URI, petToCreate, HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

    /**
     * Sends the Pet object, into the API caller methods, to create or update a Pet entity
     *
     * @param updatedPet The Pet to be created or updated
     * @return The created or updated Pet, from the response body of the API call
     */
    public Pet updatePet(Pet updatedPet) {
        requestController = new RequestController();

        return new PetMapper().mapPetFromResponse(requestController.createOrUpdateEntity(BASE_URI, updatedPet, HttpStatus.SC_OK));
    }

    /**
     * Deletes a Pet entity from the database, trough API call
     *
     * @param petToDelete The Pet to be deleted
     * @return API response object
     */
    public APIRespone deletePet(Pet petToDelete) {
        requestController = new RequestController();

        return new ApiResponseMapper().mapAPIResponseFromResponse(requestController.deleteEntity(BASE_URI + petToDelete.getId(), HttpStatus.SC_OK));
    }

    /**
     * Deletes a Pet entity from the database, trough API call and fails
     *
     * @param petID Non existent PetID
     */
    public void failTpDeletePet(String petID) {
        requestController = new RequestController();

        requestController.deleteEntity(BASE_URI + petID, HttpStatus.SC_NOT_FOUND);
    }
}
