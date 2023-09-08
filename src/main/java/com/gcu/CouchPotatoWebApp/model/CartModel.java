package com.gcu.CouchPotatoWebApp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart associated with a user.
 */
public class CartModel {

    // Unique identifier for the user
    private int userId;

    // List of items in the cart
    private List<CartItem> items;

    /**
     * Default constructor.
     */
    public CartModel() { }

    /**
     * Constructor to initialize the cart with a user ID and list of items.
     *
     * @param userId The unique identifier for the user.
     * @param items  The list of items in the cart.
     */
    public CartModel(int userId, List<CartItem> items) {
        this.userId = userId;
        this.items = items;
    }

    /**
     * Constructor to initialize the cart with a user ID and a string representation of items.
     *
     * @param userId The unique identifier for the user.
     * @param items  The string representation of items in the cart.
     */
    public CartModel(int userId, String items) {
        this.userId = userId;
        this.items = parseItemsStringToItemList(items);
    }

    // Standard getters and setters with added documentation

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setItems(String items) {
        this.items = parseItemsStringToItemList(items);
    }

    /**
     * Adds an item to the cart.
     *
     * @param item The item to be added.
     */
    public void addItem(CartItem item) {
        this.items.add(item);
    }

    /**
     * Removes an item from the cart.
     *
     * @param item The item to be removed.
     */
    public void removeItem(CartItem item) {
        this.items.remove(item);
    }

    /**
     * Converts a string representation of items to a list of CartItem objects.
     *
     * @param items The string representation of items.
     * @return      The list of CartItem objects.
     */
    private List<CartItem> parseItemsStringToItemList(String items) {
        List<CartItem> itemList = new ArrayList<>();
        if(items.contains(":")) {
            String[] itemListAsStrings = items.split(":");
            for(String item : itemListAsStrings) {
                String[] itemProperties = item.split(";");
                itemList.add(new CartItem(Integer.parseInt(itemProperties[0]), itemProperties[1], itemProperties[2], Float.parseFloat(itemProperties[3]), Integer.parseInt(itemProperties[4])));
            }
        } else if (items.contains(";")) {
            String[] itemProperties = items.split(";");
            itemList.add(new CartItem(Integer.parseInt(itemProperties[0]), itemProperties[1], itemProperties[2], Float.parseFloat(itemProperties[3]), Integer.parseInt(itemProperties[4])));
        }
        return itemList;
    }

    /**
     * Converts the list of CartItem objects to a string representation.
     *
     * @return The string representation of items in the cart.
     */
    public String itemListToString() {
        StringBuilder itemsBuilder = new StringBuilder();
        for(int i = 0; i < this.items.size(); i++) {
            itemsBuilder.append(this.items.get(i).toString());
            if(i < this.items.size() - 1) {
                itemsBuilder.append(":");
            }
        }
        return itemsBuilder.toString();
    }
}
