package com.gcu.CouchPotatoWebApp.model;

import java.util.ArrayList;
import java.util.List;

public class CartModel
{
    private int userId;

    List<CartItem> items;



    public CartModel() { }

    public CartModel(int userId, List<CartItem> items)
    {
        this.userId = userId;
        this.items = items;
    }

    public CartModel(int userId, String items)
    {
        this.userId = userId;
        this.items = ParseItemsStringToItemList(items);
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public List<CartItem> getItems()
    {
        return items;
    }

    public void setItems(List<CartItem> items)
    {
        this.items = items;
    }

    public void setItems(String items)
    {
        this.items = ParseItemsStringToItemList(items);
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    public void removeItem(CartItem item) {
        this.items.remove(item);
    }

    private List<CartItem> ParseItemsStringToItemList(String items)
    {
        List<CartItem> itemList = new ArrayList<CartItem>();
        if(items.contains(":")) {
            String[] itemListAsStrings = items.split(":");
            for(String item : itemListAsStrings)
            {
                String[] itemProperties = item.split(";");
                itemList.add(new CartItem(Integer.parseInt(itemProperties[0]), itemProperties[1], itemProperties[2], Float.parseFloat(itemProperties[3]), Integer.parseInt(itemProperties[4])));
            }
        } else if (items.contains(";")){
            String[] itemProperties = items.split(";");
            itemList.add(new CartItem(Integer.parseInt(itemProperties[0]), itemProperties[1], itemProperties[2], Float.parseFloat(itemProperties[3]), Integer.parseInt(itemProperties[4])));
        }
        return itemList;
    }

    public String ItemListToString()
    {
        List<CartItem> tempList = new ArrayList<CartItem>(this.items);
        String items = "";
        for(CartItem item : this.items)
        {
            items += item.toString();
            tempList.remove(item);
            if(!tempList.isEmpty()) {
                items += ":";
            }
        }
        return items;
    }
}
