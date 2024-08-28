package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.user.User;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTests extends UserTestBase {

    /**
     * WHEN - Trying to create a new user, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewUserSuccessfulTest() {
        LOG.info("Running: createNewUserSuccessfulTest");

        APIResponse response = userRequestHandler.createNewUser(userToCreate);

        assertThat(response.getCode())
                .as("User Creation Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        getUserByUsernameAndAssertResult(userToCreate.getUsername(), userToCreate);
    }

    /**
     * WHEN - Trying to create new Users with a List
     * THEN - All Users are created successfully
     */
    @Test
    void createNewUsersWithListSuccessfulTest() {
        LOG.info("Running: createNewUsersWithListSuccessfulTest");

        String user2YamlFilePath = "src/test/resources/testData/yaml/TestUser2Data.yaml";

        List<User> usersToCreate = new ArrayList<>();
        usersToCreate.add(userToCreate);
        usersToCreate.add(userToCreate = customObjectMapper.mapObjectFromYaml(new File(user2YamlFilePath), User.class));

        APIResponse response = userRequestHandler.createNewUsersWithList(usersToCreate);

        assertThat(response.getCode())
                .as("Create Users With List Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        getUserByUsernameAndAssertResult(usersToCreate.get(0).getUsername(), usersToCreate.get(0));
        getUserByUsernameAndAssertResult(usersToCreate.get(1).getUsername(), usersToCreate.get(1));
    }

    /**
     * WHEN - Trying to create new Users with an Array
     * THEN - All Users are created successfully
     */
    @Test
    void CreateNewUsersWithArraySuccessfulTest() {
        LOG.info("Running: CreateNewUsersWithArraySuccessfulTest");

        String user2YamlFilePath = "src/test/resources/testData/yaml/TestUser2Data.yaml";

        User[] usersToCreate = {
                userToCreate,
                customObjectMapper.mapObjectFromYaml(new File(user2YamlFilePath), User.class)
        };

        APIResponse response = userRequestHandler.createNewUsersWithArray(usersToCreate);

        assertThat(response.getCode())
                .as("Create Users With Array Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        getUserByUsernameAndAssertResult(usersToCreate[0].getUsername(), usersToCreate[0]);
        getUserByUsernameAndAssertResult(usersToCreate[1].getUsername(), usersToCreate[1]);
    }

}