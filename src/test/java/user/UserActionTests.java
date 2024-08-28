package user;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserActionTests extends UserTestBase{

    /**
     * GIVEN - User exists
     * WHEN - User tries to log in
     * THEN - User is successfully logged in
     */
    @Test
    void userLogInSuccessfulTest() {
        LOG.info("Running: userLogInSuccessfulTest");
        userRequestHandler.createNewUser(userToCreate);

         APIResponse response = userRequestHandler.logInUser(userToCreate.getUsername(), userToCreate.getPassword());

         assertThat(response.getCode())
                 .as("LogIn User Positive Test")
                 .isEqualTo(HttpStatus.SC_OK);

         assertThat(response.getMessage().contains("logged in user session:"))
                 .as("LogIn User Positive Test")
                 .isTrue();
    }

    /**
     * WHEN - User tries to log out
     * THEN - User is successfully logged out
     */
    @Test
    void userLogOutSuccessfulTest() {
        LOG.info("Running: userLogOutSuccessfulTest");

        APIResponse response = userRequestHandler.logOutUser();

        assertThat(response.getCode())
                .as("LogOut User Positive Test")
                .isEqualTo(HttpStatus.SC_OK);
        assertThat(response.getMessage())
                .as("LogOut User Positive Test")
                .isEqualTo("ok");
    }

}