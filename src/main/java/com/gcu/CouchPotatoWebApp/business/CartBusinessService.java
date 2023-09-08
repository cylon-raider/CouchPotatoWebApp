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
public class CartBusinessService {

    @Autowired
    private CartDataService cartDataService;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private UserDataService userDataService;

    /**
     * Retrieves cart items for a given username.
     *
     * @param username the username of the user.
     * @return a list of cart items.
     */
    public List<CartItem> getCartItemsByUsername(String username) {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        return cart.getItems() != null ? cart.getItems() : new ArrayList<>();
    }

    /**
     * Adds an item to a user's cart. If the cart doesn't exist, it creates one.
     *
     * @param username  the username of the user.
     * @param productId the ID of the product to add.
     */
    public void addItem(String username, int productId) {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);
        CartItem item = new CartItem(product.getProductId(), product.getProductName(), product.getProductDescription(),
                product.getProductPrice(), 1);

        if (cart.getUserId() == 0) {
            createNewCart(username, item);
        } else {
            addItemToExistingCart(cart, item);
        }
    }

    /**
     * Updates the quantity of an item in a user's cart.
     *
     * @param username  the username of the user.
     * @param productId the ID of the product to update.
     * @param quantity  the new quantity.
     */
    public void updateItem(String username, int productId, int quantity) {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);
        CartItem updatedItem = new CartItem(product.getProductId(), product.getProductName(), product.getProductDescription(),
                product.getProductPrice(), quantity);

        List<CartItem> currentItemList = cart.getItems();
        currentItemList.removeIf(item -> item.getName().equals(updatedItem.getName()));
        currentItemList.add(updatedItem);

        cart.setItems(currentItemList);
        cartDataService.update(cart);
    }

    /**
     * Deletes an item from a user's cart.
     *
     * @param username  the username of the user.
     * @param productId the ID of the product to delete.
     */
    public void deleteItem(String username, int productId) {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        ProductModel product = productDataService.getById(productId);

        cart.getItems().removeIf(item -> item.getName().equals(product.getProductName()));
        cartDataService.update(cart);
    }

    /**
     * Empties a user's cart.
     *
     * @param username the username of the user.
     * @return true if successful, false otherwise.
     */
    public boolean emptyCart(String username) {
        CartModel cart = cartDataService.getById(userDataService.getUserIdByUsername(username));
        return cartDataService.delete(cart);
    }

    private void createNewCart(String username, CartItem item) {
        List<CartItem> itemList = new ArrayList<>();
        itemList.add(item);

        CartModel newCart = new CartModel();
        newCart.setUserId(userDataService.getUserIdByUsername(username));
        newCart.setItems(itemList);

        cartDataService.create(newCart);
    }

    private void addItemToExistingCart(CartModel cart, CartItem item) {
        boolean itemExists = cart.getItems().stream().anyMatch(currentItem -> currentItem.getName().equals(item.getName()));

        if (itemExists) {
            for (CartItem currentItem : cart.getItems()) {
                if (currentItem.getName().equals(item.getName())) {
                    currentItem.setQty(currentItem.getQty() + 1);
                }
            }
        } else {
            cart.getItems().add(item);
        }

        cartDataService.update(cart);
    }
}
