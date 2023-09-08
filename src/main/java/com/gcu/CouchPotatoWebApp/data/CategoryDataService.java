package com.gcu.CouchPotatoWebApp.data;

import com.gcu.CouchPotatoWebApp.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle data operations related to the Category.
 */
@Service
public class CategoryDataService implements DataAccessInterface<CategoryModel> {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor to initialize the data source and JDBC template.
     *
     * @param dataSource The data source for the database connection.
     */
    public CategoryDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Fetch all category records from the database.
     *
     * @return List of CategoryModel objects.
     */
    @Override
    public List<CategoryModel> getAll() {
        String sql = "SELECT * FROM CATEGORY";
        List<CategoryModel> categories = new ArrayList<>();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next()) {
                categories.add(new CategoryModel(srs.getInt("CATEGORY_ID"),
                        srs.getString("CATEGORY_NAME")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Fetch a specific category record based on category ID.
     *
     * @param id The category ID.
     * @return CategoryModel object.
     */
    @Override
    public CategoryModel getById(int id) {
        String sql = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";
        CategoryModel category = new CategoryModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next()) {
                category.setCategoryId(id);
                category.setCategoryName(srs.getString("CATEGORY_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    /**
     * Create a new category record in the database.
     *
     * @param categoryModel The CategoryModel object to be added.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean create(CategoryModel categoryModel) {
        String sql = "INSERT INTO CATEGORY(CATEGORY_NAME) VALUES(?)";
        try {
            jdbcTemplate.update(sql, categoryModel.getCategoryName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Update an existing category record in the database.
     *
     * @param categoryModel The CategoryModel object with updated details.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean update(CategoryModel categoryModel) {
        String sql = "UPDATE CATEGORY SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
        try {
            jdbcTemplate.update(sql, categoryModel.getCategoryName(), categoryModel.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Delete a category record from the database.
     *
     * @param categoryModel The CategoryModel object to be deleted.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean delete(CategoryModel categoryModel) {
        String sql = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
        try {
            jdbcTemplate.update(sql, categoryModel.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
