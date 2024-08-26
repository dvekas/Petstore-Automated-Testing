package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.testng.annotations.Test;

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

        getUserByUsernameAndAssertResult(userToCreate.getUsername());
    }

    //TODO: createNewUserUnsuccessfulTest
    //TODO: createNewUsersWithListSuccessfulTest
    //TODO: createNewUsersWithListUnsuccessfulTest
    //TODO: CreateNewUsersWithArraySuccessfulTest
    //TODO: CreateNewUsersWithArrayUnsuccessfulTest
}
