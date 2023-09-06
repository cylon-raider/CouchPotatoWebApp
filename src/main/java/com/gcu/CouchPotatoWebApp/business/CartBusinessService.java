package com.gcu.CouchPotatoWebApp.business;

import com.gcu.CouchPotatoWebApp.data.CartDataService;
import com.gcu.CouchPotatoWebApp.data.ProductDataService;
import com.gcu.CouchPotatoWebApp.data.UserDataService;
import com.gcu.CouchPotatoWebApp.model.CartItem;
import com.gcu.CouchPotatoWebApp.model.CartModel;
import com.gcu.CouchPotatoWebApp.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartBusinessService
{
    @Autowired
    private CartDataService cartDataService;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private UserDataService userDataService;

    public List<CartItem> getCartItemsByUsername(String username)
    {
        List<CartItem> items = new ArrayList<CartItem>();
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        if (cart.getItems() != null)
        {
            items = cart.getItems();
            return items;
        }
        return items;
    }

    // add an item to an existing cart, create the cart if it does not exist.
    public void addItem(String username, int productId)
    {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);
        CartItem item = new CartItem(product.getProductId(), product.getProductName(), product.getProductDescription(),
                product.getProductPrice(), 1);
        CartModel newCart = new CartModel();
        List<CartItem> itemList = new ArrayList<CartItem>();
        if (cart.getUserId() == 0)
        {
            System.out.println("adding item to new cart...");

            try
            {
                itemList.add(item);
                newCart.setUserId(userDataService.getUserIdByUsername(username));
                newCart.setItems(itemList);
                cartDataService.create(newCart);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            System.out.println("adding item to existing cart...");

            try
            {
                boolean itemExists = false;

                System.out.println("checking for existing item...");

                for(CartItem currentItem : cart.getItems()) {
                    if (currentItem.getName().compareTo(item.getName()) == 0) {
                        itemExists = true;
                        cart.removeItem(currentItem);
                    }
                    if(itemExists) {
                        break;
                    }
                }

                if(itemExists)
                {
                    System.out.println("item already in cart! incrementing qty...");

                    int currentItemQty = item.getQty();
                    item.setQty(currentItemQty + 1);
                    cart.addItem(item);
                    cartDataService.update(cart);

                } else
                {
                    System.out.println("adding new item to existing cart...");

                    cart.getItems().add(item);
                    cartDataService.update(cart);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // change an item's quantity
    public void updateItem(String username, int productId, int quantity)
    {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);
        CartItem updatedItem = new CartItem(product.getProductId(), product.getProductName(), product.getProductDescription(),
                product.getProductPrice(), quantity);
        try
        {
            List<CartItem> currentItemList = cart.getItems();
            List<CartItem> newItemList = new ArrayList<CartItem>();
            newItemList.add(updatedItem);
            for (CartItem item : currentItemList)
            {
                if (!item.getName().contentEquals(updatedItem.getName()))
                {
                    newItemList.add(item);
                }
            }
            cart.setItems(newItemList);
            cartDataService.update(cart);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // delete an item from a cart
    public void deleteItem(String username, int productId)
    {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);
        try
        {
            List<CartItem> currentItemList = cart.getItems();
            List<CartItem> newItemList = new ArrayList<CartItem>();
            for (CartItem item : currentItemList)
            {
                if (!item.getName().contentEquals(product.getProductName()))
                {
                    newItemList.add(item);
                }
            }
            cart.setItems(newItemList);
            cartDataService.update(cart);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // empty cart (simply deletes entire row from database)
    public boolean emptyCart(String username)
    {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        return cartDataService.delete(cart);
    }
}
