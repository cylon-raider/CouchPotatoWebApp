package com.gcu.CouchPotatoWebApp.data;

import com.gcu.CouchPotatoWebApp.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle CRUD operations related to products.
 */
@Service
public class ProductDataService implements DataAccessInterface<ProductModel> {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor to initialize the JDBC template.
     *
     * @param dataSource The data source for the database connection.
     */
    public ProductDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Fetch all products from the database.
     *
     * @return List of products.
     */
    @Override
    public List<ProductModel> getAll() {
        String sql = "SELECT * FROM PRODUCT";
        List<ProductModel> products = new ArrayList<>();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next()) {
                products.add(new ProductModel(srs.getInt("PRODUCT_ID"),
                        srs.getString("PRODUCT_NAME"),
                        srs.getString("PRODUCT_DESCRIPTION"),
                        srs.getFloat("PRODUCT_PRICE"),
                        srs.getInt("PRODUCT_QUANTITY"),
                        srs.getString("PRODUCT_CATEGORY")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Fetch a specific product based on its ID.
     *
     * @param id The ID of the product.
     * @return The product details.
     */
    @Override
    public ProductModel getById(int id) {
        String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
        ProductModel product = new ProductModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next()){
                product.setProductId(id);
                product.setProductName(srs.getString("PRODUCT_NAME"));
                product.setProductDescription(srs.getString("PRODUCT_DESCRIPTION"));
                product.setProductPrice(srs.getFloat("PRODUCT_PRICE"));
                product.setProductQuantity(srs.getInt("PRODUCT_QUANTITY"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Add a new product to the database.
     *
     * @param productModel The product details.
     * @return true if the creation was successful, false otherwise.
     */
    @Override
    public boolean create(ProductModel productModel) {
        String sql = "INSERT INTO PRODUCT(PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_CATEGORY) VALUES(?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql, productModel.getProductName(), productModel.getProductDescription(),
                    productModel.getProductPrice(), productModel.getProductQuantity(), productModel.getProductCategory());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Update the details of an existing product in the database.
     *
     * @param productModel The updated product details.
     * @return true if the update was successful, false otherwise.
     */
    @Override
    public boolean update(ProductModel productModel) {
        String sql = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_DESCRIPTION = ?, PRODUCT_PRICE = ?, PRODUCT_QUANTITY = ?, PRODUCT_CATEGORY = ? WHERE PRODUCT_ID = ?";
        try {
            jdbcTemplate.update(sql, productModel.getProductName(), productModel.getProductDescription(),
                    productModel.getProductPrice(), productModel.getProductQuantity(), productModel.getProductCategory(), productModel.getProductId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Delete a product from the database.
     *
     * @param productModel The product to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    @Override
    public boolean delete(ProductModel productModel) {
        String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
        try {
            jdbcTemplate.update(sql, productModel.getProductId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Search for products based on a query string. The search checks the product name, description, and category.
     *
     * @param query The search query.
     * @return List of products that match the search criteria.
     */
    public List<ProductModel> findByNameContainingIgnoreCase(String query) {
        String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME LIKE ? OR PRODUCT_DESCRIPTION LIKE ? OR PRODUCT_CATEGORY LIKE ?";
        List<ProductModel> products = new ArrayList<>();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, "%" + query + "%", "%" + query + "%", "%" + query + "%");
            while (srs.next()) {
                products.add(new ProductModel(srs.getInt("PRODUCT_ID"),
                        srs.getString("PRODUCT_NAME"),
                        srs.getString("PRODUCT_DESCRIPTION"),
                        srs.getFloat("PRODUCT_PRICE"),
                        srs.getInt("PRODUCT_QUANTITY"),
                        srs.getString("PRODUCT_CATEGORY")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
