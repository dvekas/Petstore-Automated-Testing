package org.dvekas.controller.user;

import org.apache.http.HttpStatus;
import org.dvekas.controller.CustomObjectMapper;
import org.dvekas.controller.REST.RequestController;
import org.dvekas.model.APIResponse;
import org.dvekas.model.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRequests {
    static String BASE_URI = "https://petstore.swagger.io/v2/user/";

    RequestController requestController;
    CustomObjectMapper customObjectMapper;

    public UserRequests() {
        requestController = new RequestController();
        customObjectMapper = new CustomObjectMapper();
    }

    /**
     * Requests a User object from the API.
     *
     * @param userName ID of the User object, we request.
     * @return The requested User, from the response body of the API call.
     */
    public User getUserByUserName(String userName) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + userName, HttpStatus.SC_OK, null),
                User.class
        );
    }

    /**
     * Tries to request a non-existent user, via a GET request, and fails.
     * @param userName Name of the non-existent User.
     * @return Response from the API.
     */
    public APIResponse getNonExistentUser(String userName) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + userName, HttpStatus.SC_NOT_FOUND, null),
                APIResponse.class
        );
    }

    /**
     * Sends the User object, into the API caller methods, to create a new User entity
     *
     * @param userToCreate The User to be created
     * @return Response from the API.
     */
    public APIResponse createNewUser(User userToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI, userToCreate, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Creates new Users, from the given list.
     *
     * @param usersToCreate List of users to create.
     * @return Response from the API.
     */
    public APIResponse createNewUsersWithList(List<User> usersToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI + "createWithList", usersToCreate, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Creates new Users, from the given array.
     *
     * @param usersToCreate Array of users to create.
     * @return Response from the API.
     */
    public APIResponse createNewUsersWithArray(User[] usersToCreate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createNewEntity(BASE_URI + "createWithArray", usersToCreate, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Tries to log in the given user.
     * Takes in the username and password as an unencrypted string.
     *
     * @param userName  User's username.
     * @param userPassword User's password.
     * @return Response from the API.
     */
    public APIResponse logInUser(String userName, String userPassword) {

        String logInURI = BASE_URI + "login?username=" + userName + "&password=" + userPassword;


        Map<String, String> logInHeaders = new HashMap<>();
        logInHeaders.put("X-Expires-After", "2100-08-22T07:15:58.000+0000");
        logInHeaders.put("X-Rate-Limit", "200");

        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(logInURI, HttpStatus.SC_OK, logInHeaders),
                APIResponse.class
        );
    }

    /**
     * Tries to log out the logged-in user.
     *
     * @return Response from the API.
     */
    public APIResponse logOutUser() {
        return customObjectMapper.mapObjectFromResponse(
                requestController.getEntity(BASE_URI + "logout", HttpStatus.SC_OK, null),
                APIResponse.class
        );
    }

    /**
     * Sends an update request, for the selected user.
     *
     * @param userToUpdate User to update
     * @return Response from the API.
     */
    public APIResponse updateUser(User userToUpdate) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.createOrUpdateEntity(BASE_URI + userToUpdate.getUsername(), userToUpdate, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Deletes the selected user.
     *
     * @param userName Name of the user to delete.
     * @return Response from the API.
     */
    public APIResponse deleteUser(String userName) {
        return customObjectMapper.mapObjectFromResponse(
                requestController.deleteEntity(BASE_URI + userName, HttpStatus.SC_OK),
                APIResponse.class
        );
    }

    /**
     * Tries to delete a non-existent User.
     *
     * @param userName Name of the user to delete.
     */
    public void deleteUserAndFail(String userName) {
        requestController.deleteEntity(BASE_URI + userName, HttpStatus.SC_NOT_FOUND);
    }

}