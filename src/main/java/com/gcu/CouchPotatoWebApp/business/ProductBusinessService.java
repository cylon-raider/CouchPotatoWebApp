package com.gcu.CouchPotatoWebApp.business;

import com.gcu.CouchPotatoWebApp.data.ProductDataService;
import com.gcu.CouchPotatoWebApp.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBusinessService {

    @Autowired
    private ProductDataService productDataService;

    /**
     * Adds a new product to the database.
     *
     * @param productModel the product to be added.
     * @return true if the product was added successfully, false otherwise.
     */
    public boolean addProduct(ProductModel productModel){
        return productDataService.create(productModel);
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all products.
     */
    public List<ProductModel> getAll() {
        return productDataService.getAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return the product with the specified ID.
     */
    public ProductModel getById(Integer productId) {
        return productDataService.getById(productId);
    }

    /**
     * Updates the details of a product in the database.
     *
     * @param productModel the product with updated details.
     * @return true if the product was updated successfully, false otherwise.
     */
    public boolean updateProduct(ProductModel productModel){
        return productDataService.update(productModel);
    }

    /**
     * Deletes a product from the database.
     *
     * @param productModel the product to be deleted.
     * @return true if the product was deleted successfully, false otherwise.
     */
    public boolean deleteProduct(ProductModel productModel) {
        return productDataService.delete(productModel);
    }

    /**
     * Searches for products by name, description, or category, ignoring case.
     *
     * @param query the search query.
     * @return a list of products that match the search query.
     */
    public List<ProductModel> findByNameContainingIgnoreCase(String query) {
        return productDataService.findByNameContainingIgnoreCase(query);
    }
}
