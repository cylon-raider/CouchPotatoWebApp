package com.gcu.CouchPotatoWebApp.rest;

import com.gcu.CouchPotatoWebApp.business.ProductBusinessService;
import com.gcu.CouchPotatoWebApp.model.ProductList;
import com.gcu.CouchPotatoWebApp.model.ProductModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ProductRestService {

    @Autowired
    public ProductBusinessService service;

    /**
     * Endpoint to retrieve a specific product by its ID.
     *
     * @param productId the ID of the product to retrieve.
     * @return ResponseEntity containing the product or an appropriate HTTP status code.
     */
    @GetMapping(path="/getProduct/{id}")
    @ApiOperation(value = "Retrieve a product by ID")
    public ResponseEntity<?> getProduct(@PathVariable("id") Integer productId) {
        try {
            ProductModel product = service.getById(productId);
            if (product == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to retrieve all products.
     *
     * @return ResponseEntity containing a list of all products or an appropriate HTTP status code.
     */
    @GetMapping("/allProducts")
    @ApiOperation(value = "Get all products")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductModel> products = service.getAll();
            if (products == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to retrieve all products in JSON format.
     *
     * @return List of all products.
     */
    @GetMapping(path="/getJson", produces={MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get all products in JSON format")
    public List<ProductModel> getProductsAsJson() {
        return service.getAll();
    }

    /**
     * Endpoint to retrieve all products in XML format.
     *
     * @return ProductList containing all products.
     */
    @GetMapping(path="/getXml", produces ={MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get all products in XML Format")
    public ProductList getProductAsXml() {
        ProductList productList = new ProductList();
        productList.setProductList(service.getAll());
        return productList;
    }
}
