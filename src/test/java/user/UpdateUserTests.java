package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.user.User;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UpdateUserTests extends UserTestBase {

    /**
     * GIVEN - User exists in the database
     * WHEN - Trying to update user
     * THEN - Update is successful
     */
    @Test
    void updateUserSuccessfulTest() {
        LOG.info("Running: updateUserSuccessfulTest");

        userRequestHandler.createNewUser(userToCreate);

        userToCreate.setUsername(userToCreate.getUsername() + generateRandomNumber());
        APIResponse response = userRequestHandler.updateUser(userToCreate);

        assertThat(response.getCode())
                .as("Update User Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        User requestedUser = userRequestHandler.getUserByUserName(userToCreate.getUsername());

        assertThat(requestedUser)
                .as("Update User Positive Test")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringFields("username")
                .isEqualTo(userToCreate);

        assertThat(requestedUser.getUsername()).isEqualTo(userToCreate.getUsername());
    }

}