package org.jozsef.daniel.vekas.controller.Pet;

import io.restassured.response.Response;
import org.jozsef.daniel.vekas.model.factories.PetBuilder;
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
        int petId;
        String petName;
        PetStatusEnum petStatus;

        petName = getValueFromResponseBody(response, "name");

        petStatus = PetStatusEnum.getEnumByString(getValueFromResponseBody(response, "status"));

        try {
            petId = Integer.parseInt(getValueFromResponseBody(response, "id"));
            return new PetBuilder()
                    .petID(petId)
                    .name(petName)
                    .petStatus(petStatus)
                    .build();
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
        }

        return null;
    }

    private String getValueFromResponseBody(Response response, String key) {
        return response.getBody().jsonPath().getString(key);
    }
}
