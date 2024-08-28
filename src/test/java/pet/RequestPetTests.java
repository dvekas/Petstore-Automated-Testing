package pet;

import org.dvekas.model.pet.Pet;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestPetTests extends PetTestBase {

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Requesting the pet via ID
     * THEN - The correct Pet is returned
     */
    @Test
    void getPetByIDSuccessfulTest() {
        LOG.info("Running: getPetByIDSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToCreate);

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

    /**
     * GIVEN - A Pet exist in the database, with the given status
     * WHEN - Requesting the list of pets by status
     * THEN - The Pet is found in the returned list
     */
    @Test
    void getPetByStatusSuccessfulTest() {
        LOG.info("Running: getPetByStatusSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToCreate);
        List<Pet> pets = petRequestHandler.getPetsByStatus(createdPet.getStatus());

        boolean doesListContainCreatedPet = pets.stream().
                anyMatch(pet ->
                        createdPet.getId().equals(pet.getId()) &&
                        createdPet.getName().equals(pet.getName()) &&
                        createdPet.getStatus().equals(pet.getStatus())
                );

        assertThat(doesListContainCreatedPet)
                .as("Get Pets By Status Successful Test")
                .isTrue();
    }

}