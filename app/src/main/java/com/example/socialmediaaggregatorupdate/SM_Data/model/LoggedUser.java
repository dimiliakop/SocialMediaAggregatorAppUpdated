package com.example.socialmediaaggregatorupdate.SM_Data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedUser {

    private String userId;
    private String displayName;

    public LoggedUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
