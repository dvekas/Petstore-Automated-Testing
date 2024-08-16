package pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.REST.RequestHandler;
import org.dvekas.controller.pet.PetRequests;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTestBase {

    protected static final Logger LOG = LogManager.getLogger(RequestHandler.class);
    protected static final String petYamlFilePath = "src/test/resources/testdata/yaml/TestPetData.yaml";

    protected PetRequests petRequestHandler;
    protected Pet petToBeCreated;

    /**
     * Loads in data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            petToBeCreated = mapper.readValue(new File(petYamlFilePath), Pet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
                .isEqualTo(petToBeCreated);
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
