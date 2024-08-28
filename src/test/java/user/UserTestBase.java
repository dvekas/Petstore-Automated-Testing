package user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.controller.user.UserRequests;
import org.dvekas.model.user.User;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestBase {

    protected static final Logger LOG = LogManager.getLogger(UserTestBase.class);
    protected static final String userYamlFilePath = "src/test/resources/testData/yaml/TestUserData.yaml";

    protected CustomObjectMapper customObjectMapper;
    protected UserRequests userRequestHandler;
    protected User userToCreate;

    /**
     * Loads in data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        customObjectMapper = new CustomObjectMapper();
        userToCreate = customObjectMapper.mapObjectFromYaml(new File(userYamlFilePath), User.class);
        userToCreate.setUsername(userToCreate.getUsername() +  + generateRandomNumber());

        userRequestHandler = new UserRequests();
    }

    /**
     * Downloads a User (via Username) from the API and asserts if it is created as planned.
     *
     * @param userName Username of the user to be checked.
     */
    protected void getUserByUsernameAndAssertResult(String userName, User expectedUser){
        User requestedUser = userRequestHandler.getUserByUserName(userName);

        assertThat(requestedUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedUser);
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