package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestUserTests extends UserTestBase {

    /**
     * GIVEN - A User exists in the database
     * WHEN - Requesting the pet via username
     * THEN - The correct User is returned
     */
    @Test
    void getUserByUsernameSuccessfulTest() {
        LOG.info("Running: getUserByUsernameSuccessfulTest");

        userRequestHandler.createNewUser(userToCreate);

        getUserByUsernameAndAssertResult(userToCreate.getUsername(), userToCreate);
    }

    /**
     * WHEN - Trying to request a non--existent user
     * THE - An error message is returned
     */
    @Test
    void getUserByUsernameUnsuccessfulTest() {
        LOG.info("Running: getUserByUsernameUnsuccessfulTest");

        APIResponse response = userRequestHandler.getNonExistentUser("3RR0R");

        assertThat(response.getMessage())
                .as("Get User By Username Negative Test")
                .isEqualTo("User not found");
    }

}