package com.gcu.CouchPotatoWebApp.business;

import com.gcu.CouchPotatoWebApp.data.CategoryDataService;
import com.gcu.CouchPotatoWebApp.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBusinessService
{
    @Autowired
    private CategoryDataService categoryDataService;

    public List<CategoryModel> getAllCategories() {
        return categoryDataService.getAll();
    }

    public void addCategory(CategoryModel category) {
        categoryDataService.create(category);
    }

    public void deleteCategory(CategoryModel category) {
        categoryDataService.delete(category);
    }
}
