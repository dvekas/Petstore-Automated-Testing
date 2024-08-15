package pet;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.REST.RequestHandler;
import org.dvekas.controller.pet.PetRequests;
import org.dvekas.model.APIResponse;
import org.dvekas.model.factories.pet.PetBuilder;
import org.dvekas.model.pet.Pet;
import org.dvekas.model.pet.PetStatusEnum;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

public class CreateGetUpdateDeletePetTests {

    private static final Logger LOG = LogManager.getLogger(RequestHandler.class);

    private PetRequests petRequestHandler;
    private Pet petToBeCreated;

    /**
     * Creates data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        petToBeCreated = new PetBuilder()
                .id(String.valueOf(generateRandomID()))
                .name("Barkspawn")
                .petStatus(PetStatusEnum.AVAILABLE)
                .build();

        petRequestHandler = new PetRequests();
    }

    /**
     * WHEN - Trying to create a new pet, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewPetSuccessfulTest() {
        LOG.info("Running: createNewPetSuccessfulTest");
        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);

        assertThat(createdPet).as("Pet Creation Positive Test").withFailMessage("Creation of new Pet is unsuccessful").usingRecursiveComparison().isEqualTo(petToBeCreated);
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
        getNonExistentPetAndAssertResult(String.valueOf(generateRandomID()));
    }

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to change the Pet
     * THEN - The correct Pet is changed, as expected
     */
    @Test
    void updateExistingPetSuccessfulTest() {
        LOG.info("Running: updateExistingPetSuccessfulTest");
        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);
        petToBeCreated.setStatus(PetStatusEnum.SOLD);
        Pet updatedPet = petRequestHandler.updatePet(petToBeCreated);

        assertThat(updatedPet).as("Pet Information Update Positive Test").withFailMessage("Updating the Pet is unsuccessful").usingRecursiveComparison().isEqualTo(petToBeCreated);
        assertThat(updatedPet.getStatus()).as("Pet Information Update Positive Test").withFailMessage("Updating the Pet is unsuccessful").isNotEqualTo(createdPet.getStatus());
        assertThat(updatedPet).as("Pet Information Update Positive Test").withFailMessage("Updating the Pet is unsuccessful").usingRecursiveComparison().isEqualTo(petRequestHandler.getPetByID(petToBeCreated.getId()));
        getPetByIDAndAssertResult(updatedPet.getId());
    }

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to delete the Pet
     * THEN - The correct Pet is deleted
     */
    @Test
    void deleteExistingPetSuccessfulTest() {
        LOG.info("Running: deleteExistingPetSuccessfulTest");
        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);

        APIResponse response = petRequestHandler.deletePet(createdPet);
        assertThat(response.getCode()).as("Pet Deletion Positive Test").withFailMessage("Pet Deletion Unsuccessful").isEqualTo(HttpStatus.SC_OK);
        assertThat(response.getMessage()).as("Pet Deletion Positive Test").withFailMessage("Pet Deletion Unsuccessful").isEqualTo(String.valueOf(createdPet.getId()));

        getNonExistentPetAndAssertResult(createdPet.getId());
    }

    /**
     * GIVEN - A Pet does not exist in the database, with the given ID
     * WHEN - Trying to delete the Pet with the non-existent ID
     * THEN - The 404 error message is returned
     */
    @Test
    void deleteExistingPetUnsuccessfulTest() {
        LOG.info("Running: deleteExistingPetUnsuccessfulTest");
        petRequestHandler.failToDeletePet(petToBeCreated.getId());
    }

    private void getPetByIDAndAssertResult(String petID){
        Pet requestedPet = petRequestHandler.getPetByID(petID);

        assertThat(requestedPet).usingRecursiveComparison().isEqualTo(petToBeCreated);
    }

    private void getNonExistentPetAndAssertResult(String petID) {
        APIResponse response = petRequestHandler.getNonExistentPetByID(petID);

        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getMessage()).isEqualTo("Pet not found");
    }

    /**
     * Generates a random number, between 1000 and 90000, to serve as ID.
     *
     * @return Generated number
     */
    private static int generateRandomID() {
        Random rnd = new Random();
        return 1000 + rnd.nextInt(90000);
    }
}
