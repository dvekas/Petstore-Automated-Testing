package Pet;

import org.jozsef.daniel.vekas.controller.Pet.PetRequests;
import org.jozsef.daniel.vekas.model.factories.PetBuilder;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

public class CreateUpdateDeletePetTests {

    private Pet petToBeCreated;
    private Pet createdPet;

    /**
     * Creates data that is needed to the tests
     */
    @BeforeClass
    void initialize() {
        petToBeCreated = new PetBuilder()
                .petID(generateRandomID())
                .name("Barkspawn")
                .petStatus(PetStatusEnum.AVAILABLE)
                .build();
    }

    /**
     * WHEN - Trying to create a new pet, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewPetSuccessfulTest() {
        System.out.println("Running: createNewPetSuccessfulTest");
        createdPet = new PetRequests().createNewPet(petToBeCreated);

        assertThat(createdPet).as("Pet Creation Positive Test").withFailMessage("Creation of new Pet is unsuccessful").usingRecursiveComparison().isEqualTo(petToBeCreated);
        testSuccessfulConsoleMessage();
    }

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Requesting the pet via ID
     * THEN - The correct Pet is returned
     */
    @Test (dependsOnMethods={"createNewPetSuccessfulTest"})
    void getPetByIDSuccessfulTest() {
        System.out.println("Running: createNewPetSuccessfulTest");
        Pet requestedPet = new PetRequests().getPetByID(createdPet.getId());

        assertThat(requestedPet).as("Pet Information Download Positive Test").withFailMessage("Creation of new Pet is unsuccessful").usingRecursiveComparison().isEqualTo(createdPet);
        testSuccessfulConsoleMessage();
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

    private void testSuccessfulConsoleMessage() {
        System.out.println("Test successful");
        System.out.println("----------------------\n");
    }
}
