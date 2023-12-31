package com.gcu.CouchPotatoWebApp.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;


/**
 * Represents a product with its details.
 */
public class ProductModel {

    // Unique identifier for the product
    private Integer productId;

    // Name of the product
    @ApiModelProperty(value = "The product name", example = "Cheese")
    private String productName;

    // Description of the product
    @ApiModelProperty(value = "Product Description", example = "Lorem Ipsum")
    private String productDescription;

    // Price of the product
    @Range(min=1, max=1000000, message="Product price must be between 1 and 10 characters")
    @ApiModelProperty(value = "Product Price", example = "1.00")
    private Float productPrice;

    // Quantity of the product available
    @Range(min=1, max=1000, message="Product quantity must be between 1 and 10 characters")
    @ApiModelProperty(value = "Quantity of product available", example = "10")
    private Integer productQuantity;

    // Category of the product
    @ApiModelProperty(value="Category for product search", example="Apparel")
    private String productCategory;

    // Constructors
    public ProductModel(String productName, String productDescription, Float productPrice, Integer productQuantity, String productCategory) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }

    public ProductModel(Integer productId, String productName, String productDescription, Float productPrice, Integer productQuantity, String productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
    }

    public ProductModel() { }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String category) {
        this.productCategory = category;
    }
}
