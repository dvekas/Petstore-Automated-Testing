package user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.controller.user.UserRequests;
import org.dvekas.model.user.User;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestBase {

    protected static final Logger LOG = LogManager.getLogger(UserTestBase.class);
    protected static final String userYamlFilePath = "src/test/resources/testData/yaml/TestUserData.yaml";

    protected UserRequests userRequestHandler;
    protected User userToCreate;

    /**
     * Loads in data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        userToCreate = customObjectMapper.mapObjectFromYaml(new File(userYamlFilePath), User.class);

        userRequestHandler = new UserRequests();
    }

    /**
     * Downloads a User (via ID) from the API and asserts if it is created as planned.
     *
     * @param userName ID of the user to be tested.
     */
    protected void getUserByUsernameAndAssertResult(String userName){
        User requestedUser = userRequestHandler.getUserByUserName(userName);

        assertThat(requestedUser)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(userToCreate);
    }

}
