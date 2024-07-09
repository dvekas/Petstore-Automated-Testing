package org.jozsef.daniel.vekas.controller.pet;

import io.restassured.response.Response;
import org.jozsef.daniel.vekas.model.factories.pet.PetBuilder;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;

public class PetMapper {

    /**
     * Creates a Pet object, from the body of the given Response object
     *
     * @param response Response object, with a pet's data in the body, as JSON
     * @return The created Pet object
     */
    public Pet mapPetFromResponse(Response response) {
        String petId;
        String petName;
        PetStatusEnum petStatus;

        petId = getValueFromResponseBody(response, "id");
        petName = getValueFromResponseBody(response, "name");
        petStatus = PetStatusEnum.getEnumByString(getValueFromResponseBody(response, "status"));

        return new PetBuilder()
                    .petID(petId)
                    .name(petName)
                    .petStatus(petStatus)
                    .build();

    }

    private String getValueFromResponseBody(Response response, String key) {
        return response.getBody().jsonPath().getString(key);
    }
}
