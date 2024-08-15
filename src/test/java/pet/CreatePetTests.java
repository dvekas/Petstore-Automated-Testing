package pet;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePetTests extends PetTestBase{

    /**
     * WHEN - Trying to create a new pet, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewPetSuccessfulTest() {
        LOG.info("Running: createNewPetSuccessfulTest");
        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);

        assertThat(createdPet)
                .as("Pet Creation Positive Test")
                .withFailMessage("Creation of new Pet is unsuccessful")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(petToBeCreated);

        getPetByIDAndAssertResult(createdPet.getId());
    }

    /**
     * WHEN - Trying to create a new pet, via an API call, with faulty data
     * THEN - Creating it is unsuccessful
     */
    @Test
    void createNewPetUnsuccessfulTest() {
        LOG.info("Running: createNewPetUnsuccessfulTest");
        petToBeCreated.setId("ERROR");
        APIResponse response = petRequestHandler.failToCreatePet(petToBeCreated);

        assertThat(response.getCode()).as("Pet Creation Negative Test").isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertThat(response.getMessage()).as("Pet Creation Negative Test").isEqualTo("something bad happened");
    }

    /**
     * Downloads a Pet (via ID) from the API and asserts if it is created as planned.
     *
     * @param petID ID of the pet to be tested.
     */
    private void getPetByIDAndAssertResult(String petID){
        Pet requestedPet = petRequestHandler.getPetByID(petID);

        assertThat(requestedPet)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(petToBeCreated);
    }
}
