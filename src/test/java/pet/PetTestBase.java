package pet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.controller.pet.PetRequests;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTestBase {

    protected static final Logger LOG = LogManager.getLogger(PetTestBase.class);
    protected static final String petYamlFilePath = "src/test/resources/testData/yaml/TestPetData.yaml";

    protected PetRequests petRequestHandler;
    protected Pet petToCreate;

    /**
     * Loads in data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        petToCreate = customObjectMapper.mapObjectFromYaml(new File(petYamlFilePath), Pet.class);

        petRequestHandler = new PetRequests();
    }

    /**
     * Downloads a Pet (via ID) from the API and asserts if it is created as planned.
     *
     * @param petID ID of the pet to be tested.
     */
    protected void getPetByIDAndAssertResult(String petID){
        Pet requestedPet = petRequestHandler.getPetByID(petID);

        assertThat(requestedPet)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(petToCreate);
    }

    /**
     * Requests a Pet with ain incorrect ID.
     *
     * @param petID ID for a non-existent Pet.
     */
    protected void getNonExistentPetAndAssertResult(String petID) {
        APIResponse response = petRequestHandler.getNonExistentPetByID(petID);

        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getMessage()).isEqualTo("Pet not found");
    }

    /**
     * Returns a random integer between 1000 and 90000.
     *
     * @return Random integer
     */
    protected int generateRandomNumber() {
        Random rnd = new Random();
       return 1000 + rnd.nextInt(90000);
    }

}
