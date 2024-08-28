package pet;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeletePetTests extends PetTestBase {

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to delete the Pet
     * THEN - The correct Pet is deleted
     */
    @Test
    void deleteExistingPetSuccessfulTest() {
        LOG.info("Running: deleteExistingPetSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToCreate);
        APIResponse response = petRequestHandler.deletePet(createdPet);

        assertThat(response.getCode())
                .as("Pet Deletion Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        assertThat(response.getMessage())
                .as("Pet Deletion Positive Test")
                .isEqualTo(String.valueOf(createdPet.getId()));

        getNonExistentPetAndAssertResult(createdPet.getId());
    }

    /**
     * GIVEN - A Pet does not exist in the database, with the given ID
     * WHEN - Trying to delete the Pet with the non-existent ID
     * THEN - The 404 error message is returned
     */
    @Test
    void deleteNonExistingPetUnsuccessfulTest() {
        LOG.info("Running: deleteNonExistingPetUnsuccessfulTest");

        petRequestHandler.failToDeletePet(String.valueOf(generateRandomNumber()));
    }

}