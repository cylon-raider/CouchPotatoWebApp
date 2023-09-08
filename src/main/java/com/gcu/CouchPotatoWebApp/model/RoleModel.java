package com.gcu.CouchPotatoWebApp.model;

/**
 * Represents a user role in the system.
 */
public class RoleModel {

    // Name of the role (e.g., "ADMIN", "USER")
    private String roleName;

    /**
     * Gets the name of the role.
     *
     * @return roleName - the name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role.
     *
     * @param roleName - the name to set for the role.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
