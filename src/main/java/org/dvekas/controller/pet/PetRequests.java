package org.dvekas.controller.pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.dvekas.controller.REST.RequestController;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.dvekas.model.pet.PetStatusEnum;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PetRequests {

    static String BASE_URI = "https://petstore.swagger.io/v2/pet/";
    RequestController requestController;
    CustomObjectMapper customObjectMapper;

    public PetRequests() {
        requestController = new RequestController();
        customObjectMapper = new CustomObjectMapper();
    }

    /**
     * Requests a Pet object from the API.
     *
     * @param petID ID of the Pet object, we request.
     * @return The requested Pet, from the response body of the API call.
     */
    public Pet getPetByID(String petID) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + petID, HttpStatus.SC_OK, null),
                Pet.class
        );
    }

    /**
     * Requests a list of Pets from the API based on status.
     *
     * @param petStatus The status the returned pets have.
     * @return List of Pet objects.
     */
    public List<Pet> getPetsByStatus(PetStatusEnum petStatus) {

        String URI = BASE_URI + "findByStatus?status=" + petStatus.getStatusName();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Pet> pets;

        String responseBody = requestController.getEntity(URI,HttpStatus.SC_OK, null).getBody().asPrettyString();

        try {
            pets = Arrays.asList(objectMapper.readValue(responseBody, Pet[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return pets;
    }

    /**
     * Requests a Pet object from the API.
     *
     * @param petID ID of the Pet object, we request.
     * @return API response object.
     */
    public APIResponse getNonExistentPetByID(String petID) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + petID, HttpStatus.SC_NOT_FOUND, null),
                APIResponse.class
        );
    }

    /**
     * Sends the Pet object, into the API caller methods, to create a new Pet entity.
     *
     * @param petToCreate The Pet to be created
     * @return The created Pet, from the response bod.y of the API call.
     */
    public Pet createNewPet(Pet petToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI, petToCreate, HttpStatus.SC_OK),
                Pet.class
        );
    }

    /**
     * Sends the Pet object, into the API caller methods, to unsuccessfully create a new Pet entity.
     *
     * @param petToCreate The Pet to be created.
     * @return API response object.
     */
    public APIResponse failToCreatePet(Pet petToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI, petToCreate, HttpStatus.SC_INTERNAL_SERVER_ERROR),
                APIResponse.class
        );
    }

    /**
     * Sends the given file to the API caller methods, to successfully upload it to the database.
     *
     * @param petID ID of the pet, we want to upload the picture to.
     * @param file The file we want to upload.
     * @param additionalMetaData Additional metadata, for the upload.
     * @return API response object.
     */
    public APIResponse uploadPictureToPet(String petID, File file, String additionalMetaData) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.uploadFile(BASE_URI + petID + "/uploadImage", file, HttpStatus.SC_OK, additionalMetaData),
                APIResponse.class
        );
    }

    /**
     * Sends the Pet object, into the API caller methods, to create or update a Pet entity.
     *
     * @param petToUpdate The Pet to be created or updated.
     * @return The created or updated Pet, from the response body of the API call.
     */
    public Pet updatePet(Pet petToUpdate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createOrUpdateEntity(BASE_URI, petToUpdate, HttpStatus.SC_OK),
                Pet.class
        );
    }

    /**
     * Updates a Pet, with Form Data
     *
     * @param petToUpdate The Pet to be created or updated.
     * @return  API response object.
     */
    public APIResponse updatePetViaPost(Pet petToUpdate) {
        String formData = "name=" + petToUpdate.getName() + "&status=" + petToUpdate.getStatus().getStatusName();

        return customObjectMapper.mapObjectFromResponse(
                requestController.updateEntityViaPostAndFormData(BASE_URI + petToUpdate.getId(), formData, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Tries to update a non-existent PET
     *
     * @param nonExistentPetID ID of the non-existent Pet.
     * @return  API response object.
     */
    public APIResponse failToUpdatePetViaPost(String nonExistentPetID) {
        String formData = "name=ERROR&status=" + PetStatusEnum.sold;

        return customObjectMapper.mapObjectFromResponse(
                requestController.updateEntityViaPostAndFormData(BASE_URI + nonExistentPetID, formData, HttpStatus.SC_NOT_FOUND),
                APIResponse.class
        );
    }

    /**
     * Deletes a Pet entity from the database, trough API call.
     *
     * @param petToDelete The Pet to be deleted.
     * @return API response object.
     */
    public APIResponse deletePet(Pet petToDelete) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.deleteEntity(BASE_URI + petToDelete.getId(), HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Deletes a Pet entity from the database, trough API call and fails.
     *
     * @param petID Non existent PetID.
     */
    public void failToDeletePet(String petID) {
        requestController.deleteEntity(BASE_URI + petID, HttpStatus.SC_NOT_FOUND);
    };

}