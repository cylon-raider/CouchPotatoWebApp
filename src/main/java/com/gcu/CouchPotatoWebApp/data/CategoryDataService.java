package com.gcu.CouchPotatoWebApp.data;

import com.gcu.CouchPotatoWebApp.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDataService implements DataAccessInterface<CategoryModel>
{
    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CategoryDataService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<CategoryModel> getAll()
    {
        String sql = "SELECT * FROM CATEGORY";
        List<CategoryModel> categories = new ArrayList<CategoryModel>();
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

    @Override
    public CategoryModel getById(int id)
    {
        String sql = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";
        CategoryModel category = new CategoryModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next()){
                category.setCategoryId(id);
                category.setCategoryName(srs.getString("CATEGORY_NAME"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean create(CategoryModel categoryModel)
    {
        String sql = "INSERT INTO CATEGORY(CATEGORY_NAME) VALUES(?)";
        try {
            jdbcTemplate.update(sql, categoryModel.getCategoryName());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(CategoryModel categoryModel)
    {
        // NOT USED !!!

        return false;
    }

    @Override
    public boolean delete(CategoryModel categoryModel)
    {
        String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
        try {
            jdbcTemplate.update(sql, categoryModel.getCategoryId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
