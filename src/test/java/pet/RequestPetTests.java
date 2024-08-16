package pet;

import org.dvekas.model.pet.Pet;
import org.testng.annotations.Test;

public class RequestPetTests  extends PetTestBase {

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Requesting the pet via ID
     * THEN - The correct Pet is returned
     */
    @Test
    void getPetByIDSuccessfulTest() {
        LOG.info("Running: getPetByIDSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);

        getPetByIDAndAssertResult(createdPet.getId());
    }

    /**
     * GIVEN - A Pet does not exist in the database, with the given ID
     * WHEN - Requesting the pet via ID
     * THEN - 404 code is returned
     */
    @Test
    void getPetByIDUnsuccessfulTest() {
        LOG.info("Running: getPetByIDUnsuccessfulTest");

        getNonExistentPetAndAssertResult(String.valueOf(generateRandomNumber()));
    }

}
