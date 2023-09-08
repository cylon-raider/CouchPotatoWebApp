package com.gcu.CouchPotatoWebApp.data;

import java.util.List;

/**
 * Generic data access interface to define CRUD operations.
 *
 * @param <T> The type of object this interface will operate on.
 */
public interface DataAccessInterface<T> {

    /**
     * Fetch all records from the database.
     *
     * @return List of objects of type T.
     */
    List<T> getAll();

    /**
     * Fetch a specific record based on its ID.
     *
     * @param id The ID of the record.
     * @return An object of type T.
     */
    T getById(int id);

    /**
     * Create a new record in the database.
     *
     * @param t The object of type T to be added.
     * @return true if the creation was successful, false otherwise.
     */
    boolean create(T t);

    /**
     * Update an existing record in the database.
     *
     * @param t The object of type T with updated details.
     * @return true if the update was successful, false otherwise.
     */
    boolean update(T t);

    /**
     * Delete a record from the database.
     *
     * @param t The object of type T to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean delete(T t);
}
