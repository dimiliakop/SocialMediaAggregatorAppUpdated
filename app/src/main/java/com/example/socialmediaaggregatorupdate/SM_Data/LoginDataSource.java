package com.example.socialmediaaggregatorupdate.SM_Data;

import com.example.socialmediaaggregatorupdate.SM_Data.model.LoggedUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedUser fakeUser =
                    new LoggedUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
