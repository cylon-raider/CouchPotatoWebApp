package com.gcu.CouchPotatoWebApp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Represents the login credentials for a user.
 */
public class LoginModel {

    // Unique identifier for the user
    private int id;

    // Username for the user
    @NotNull(message="User name is a required field")
    @Size(min=1, max=32, message="User name must be between 1 and 32 characters")
    private String username;

    // Password for the user
    @NotNull(message="Password is a required field")
    @Size(min=1, max=32, message="Password must be between 1 and 32 characters")
    private String password;

    // Standard getters and setters with added documentation

    /**
     * Gets the unique identifier for the user.
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param id The user's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
