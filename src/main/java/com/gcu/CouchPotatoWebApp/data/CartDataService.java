package com.gcu.CouchPotatoWebApp.data;

import com.gcu.CouchPotatoWebApp.model.CartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartDataService implements DataAccessInterface<CartModel>
{
    @SuppressWarnings("unused")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CartDataService(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<CartModel> getAll()
    {
        String sql = "SELECT * FROM CART";
        List<CartModel> cart = new ArrayList<CartModel>();
        try
        {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while (srs.next())
            {
                cart.add(new CartModel(srs.getInt("USER_ID"), srs.getString("CONTENTS")));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public CartModel getById(int id)
    {
        String sql = "SELECT * FROM CART WHERE USER_ID = ?";
        CartModel cart = new CartModel();
        try {
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id);
            while (srs.next()){
                cart.setUserId(id);
                cart.setItems(srs.getString("CONTENTS"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public boolean create(CartModel cart)
    {
        String sql = "INSERT INTO CART(USER_ID, CONTENTS) VALUES(?,?)";
        String cartContents = cart.ItemListToString();
        try
        {
            jdbcTemplate.update(sql, cart.getUserId(), cartContents);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(CartModel cart)
    {
        String sql = "UPDATE CART SET CONTENTS = ? WHERE USER_ID = ?";
        String cartContents = cart.ItemListToString();
        try
        {
            jdbcTemplate.update(sql, cartContents, cart.getUserId());
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(CartModel cart)
    {
        String sql = "DELETE FROM CART WHERE USER_ID = ?";
        try {
            jdbcTemplate.update(sql, cart.getUserId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
