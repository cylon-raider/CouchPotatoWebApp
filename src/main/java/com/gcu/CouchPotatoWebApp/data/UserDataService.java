package com.gcu.CouchPotatoWebApp.data;

import com.gcu.CouchPotatoWebApp.model.LoginModel;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for CRUD operations on User data.
 */
@Service
public class UserDataService implements DataAccessInterface<UserModel> {

    private static final String TABLE_NAME = "user"; // changed from USER
    private final JdbcTemplate jdbcTemplate;


    /**
     * Constructor to initialize the data source and JDBC template.
     *
     * @param dataSource The data source for the database connection.
     */
    public UserDataService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);


    }

    /**
     * Fetches all users from the database.
     *
     * @return List of all users.
     */
    @Override
    public List<UserModel> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        List<UserModel> users = new ArrayList<>();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next()) {
                users.add(new UserModel(srs.getInt("USER_ID"), srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"),
                        srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"), srs.getString("USERNAME"),
                        srs.getString("PASSWORD"), srs.getBoolean("IS_ACTIVE"), srs.getInt("ROLE_ID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Fetches a user by their ID.
     *
     * @param id The user's ID.
     * @return The user with the given ID.
     */
    @Override
    public UserModel getById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE USER_ID = ?";
        UserModel user = new UserModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next()) {
                user.setUserId(id);
                user.setFirstName(srs.getString("FIRST_NAME"));
                user.setLastName(srs.getString("LAST_NAME"));
                user.setEmail(srs.getString("EMAIL"));
                user.setPhoneNumber(srs.getString("PHONE_NUMBER"));
                user.setUsername(srs.getString("USERNAME"));
                user.setPassword(srs.getString("PASSWORD"));
                user.setRoleId(srs.getInt("ROLE_ID"));
                user.setActive(srs.getBoolean("IS_ACTIVE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user to be created.
     * @return True if the user was created successfully, false otherwise.
     */
    @Override
    public boolean create(UserModel user) {
        String sql = "INSERT INTO " + TABLE_NAME + "(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD) VALUES (?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                    user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user The user with updated details.
     * @return True if the user was updated successfully, false otherwise.
     */
    @Override
    public boolean update(UserModel user) {
        String sql = "UPDATE " + TABLE_NAME + " SET FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PHONE_NUMBER = ?, USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(),
                    user.getUsername(), user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Deletes a user from the database.
     *
     * @param userModel The user to be deleted.
     * @return True if the user was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(UserModel userModel) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE USER_ID = ?";
        try {
            jdbcTemplate.update(sql, userModel.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Fetches a user's login details by their username.
     *
     * @param username The username of the user.
     * @return The login details of the user.
     */
    public LoginModel findByUsername(String username) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?";
        LoginModel loginModel = new LoginModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, username);
            while (srs.next()) {
                loginModel.setId(srs.getInt("USER_ID"));
                loginModel.setUsername(srs.getString("USERNAME"));
                loginModel.setPassword(srs.getString("PASSWORD"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginModel;
    }

    /**
     * Fetches a user's authority details by their username.
     *
     * @param username The username of the user.
     * @return The user's authority details.
     */
    public UserModel getUserAuthority(String username) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?";
        UserModel user = new UserModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, username);
            while (srs.next()) {
                user.setActive(srs.getBoolean("IS_ACTIVE"));
                user.setRoleId(srs.getInt("ROLE_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Fetches a user's ID by their username.
     *
     * @param username The username of the user.
     * @return The ID of the user.
     */
    public int getUserIdByUsername(String username) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?";
        int userId = 0;
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, username);
            while (srs.next()) {
                userId = srs.getInt("USER_ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }
}
