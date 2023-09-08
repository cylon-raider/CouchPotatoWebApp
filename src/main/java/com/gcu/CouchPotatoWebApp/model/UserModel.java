package com.gcu.CouchPotatoWebApp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a user in the system.
 */
public class UserModel {

    // Unique identifier for the user
    private int userId;

    // First name of the user
    @NotNull(message="First name is a required field")
    @Size(min=1, max=32, message="First name must be between 1 and 32 characters")
    private String firstName;

    // Last name of the user
    @NotNull(message="Last name is a required field")
    @Size(min=1, max=32, message="Last name must be between 1 and 32 characters")
    private String lastName;

    // Email address of the user
    @NotNull(message="Email is a required field")
    @Size(min=1, max=32, message="Email must be between 1 and 32 characters")
    private String email;

    // Phone number of the user
    @NotNull(message="Phone Number is required")
    @Size(min=1, max=10, message="Phone Number must be between 1 and 10 characters")
    private String phoneNumber;

    // Username for the user's account
    @NotNull(message="User name is a required field")
    @Size(min=1, max=32, message="User name must be between 1 and 32 characters")
    private String username;

    // Password for the user's account
    @NotNull(message="Password is a required field")
    @Size(min=1, max=32, message="Password must be between 1 and 32 characters")
    private String password;

    // Indicates if the user's account is active or not
    private boolean isActive;

    // Role ID associated with the user's account
    private int roleId;

    /**
     * Constructor with parameters for creating a new user.
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
     * Constructor with all parameters including userId and roleId.
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

    /**
     * Default constructor.
     */
    public UserModel() {
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
