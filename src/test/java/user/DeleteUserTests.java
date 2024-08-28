package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteUserTests extends UserTestBase {

    /**
     * GIVEN - User exists in the database
     * WHEN - Trying to delete the User
     * THEN - Deletion is successful
     */
    @Test
    void deleteUserSuccessfulTest() {
        LOG.info("Running: deleteUserSuccessfulTest");

        userRequestHandler.createNewUser(userToCreate);
        APIResponse response = userRequestHandler.deleteUser(userToCreate.getUsername());

        assertThat(response.getCode())
                .as("Delete User Positive Test")
                .isEqualTo(HttpStatus.SC_OK);

        assertThat(response.getMessage())
                .as("Delete User Positive Test")
                .isEqualTo(userToCreate.getUsername());

        APIResponse noUserResponse = userRequestHandler.getNonExistentUser(userToCreate.getUsername());

        assertThat(noUserResponse.getCode())
                .as("Delete User Positive Test")
                .isEqualTo(1);

        assertThat(noUserResponse.getMessage())
                .as("Delete User Positive Test")
                .isEqualTo("User not found");
    }

    /**
     * WHEN - Trying to delete a non-existent user
     * THEN - 404 returned
     */
    @Test
    void deleteUserUnsuccessfulTest() {
        LOG.info("Running: deleteUserUnsuccessfulTest");

        userRequestHandler.deleteUserAndFail(userToCreate.getUsername());
    }

}