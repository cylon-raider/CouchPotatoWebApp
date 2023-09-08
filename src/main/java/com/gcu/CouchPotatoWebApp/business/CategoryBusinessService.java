package com.gcu.CouchPotatoWebApp.business;

import com.gcu.CouchPotatoWebApp.data.CategoryDataService;
import com.gcu.CouchPotatoWebApp.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBusinessService {

    @Autowired
    private CategoryDataService categoryDataService;

    /**
     * Retrieves all categories from the data service.
     *
     * @return a list of all categories.
     */
    public List<CategoryModel> getAllCategories() {
        return categoryDataService.getAll();
    }

    /**
     * Adds a new category using the data service.
     *
     * @param category the category to be added.
     */
    public void addCategory(CategoryModel category) {
        categoryDataService.create(category);
    }

    /**
     * Deletes a category using the data service.
     *
     * @param category the category to be deleted.
     */
    public void deleteCategory(CategoryModel category) {
        categoryDataService.delete(category);
    }
}
