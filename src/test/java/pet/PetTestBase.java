package pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.REST.RequestHandler;
import org.dvekas.controller.pet.PetRequests;
import org.dvekas.model.pet.Pet;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

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
}
