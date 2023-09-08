package com.gcu.CouchPotatoWebApp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a user in the system.
 */
public class UserModel {

    private int userId;

    @NotNull(message="First name is a required field")
    @Size(min=1, max=32, message="First name must be between 1 and 32 characters")
    private String firstName;

    @NotNull(message="Last name is a required field")
    @Size(min=1, max=32, message="Last name must be between 1 and 32 characters")
    private String lastName;

    @NotNull(message="Email is a required field")
    @Size(min=1, max=32, message="Email must be between 1 and 32 characters")
    private String email;

    @NotNull(message="Phone Number is required")
    @Size(min=1, max=10, message="Phone Number must be between 1 and 10 characters")
    private String phoneNumber;

    @NotNull(message="User name is a required field")
    @Size(min=1, max=32, message="User name must be between 1 and 32 characters")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min=1, max=32, message="Password must be between 1 and 32 characters")
    private String password;

    // Indicates if the user is active or not
    private boolean isActive;

    // Role ID associated with the user (e.g., Admin, User)
    private int roleId;

    // Constructors, getters, and setters follow...

    /**
     * Default constructor.
     */
    public UserModel() {}

    /**
     * Constructor to initialize user details without user ID, role, and activity status.
     */
    public UserModel(String firstName, String lastName, String email, String phoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor to initialize all user details.
     */
    public UserModel(int userId, String firstName, String lastName, String email, String phoneNumber, String username, String password, boolean isActive, int roleId) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    // Getters and setters for each field with appropriate comments...

}
