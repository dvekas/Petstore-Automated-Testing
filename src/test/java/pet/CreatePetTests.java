package pet;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePetTests extends PetTestBase {

    /**
     * WHEN - Trying to create a new pet, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewPetSuccessfulTest() {
        LOG.info("Running: createNewPetSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToCreate);

        assertThat(createdPet)
                .as("Pet Creation Positive Test")
                .withFailMessage("Creation of new Pet is unsuccessful")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(petToCreate);

        getPetByIDAndAssertResult(createdPet.getId());
    }

    /**
     * WHEN - Trying to create a new pet, via an API call, with faulty data
     * THEN - Creating it is unsuccessful
     */
    @Test
    void createNewPetUnsuccessfulTest() {
        LOG.info("Running: createNewPetUnsuccessfulTest");

        petToCreate.setId("ERROR");
        APIResponse response = petRequestHandler.failToCreatePet(petToCreate);

        assertThat(response.getCode()).as("Pet Creation Negative Test").isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertThat(response.getMessage()).as("Pet Creation Negative Test").isEqualTo("something bad happened");
    }

}
