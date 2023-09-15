package com.gcu.CouchPotatoWebApp.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Represents a product category.
 */
public class CategoryModel {

    // Unique identifier for the category
    private Integer categoryId;

    // Name of the category
    @ApiModelProperty(value="Product Category", example="Apparel")
    private String categoryName;

    /**
     * Default constructor.
     */
    public CategoryModel() { }

    /**
     * Constructor to initialize the category with a name.
     *
     * @param name The name of the category.
     */
    public CategoryModel(String name) {
        this.categoryName = name;
    }

    /**
     * Constructor to initialize the category with an ID and name.
     *
     * @param id   The unique identifier for the category.
     * @param name The name of the category.
     */
    public CategoryModel(int id, String name) {
        this.categoryId = id;
        this.categoryName = name;
    }

    // Standard getters and setters with added documentation

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
