package com.gcu.CouchPotatoWebApp.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryModel
{
    private Integer categoryId;

    @NotNull(message="Add a category to help users find products")
    @Size(min=1, max=32, message="Category name must be between 1 and 32 characters")
    @ApiModelProperty(value="Product Category", example="Apparel")
    private String categoryName;

    public CategoryModel() { }

    public CategoryModel(String name) {
        this.categoryName = name;
    }

    public CategoryModel(int id, String name) {
        this.setCategoryId(id);
        this.categoryName = name;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
