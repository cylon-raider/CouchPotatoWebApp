package com.gcu.CouchPotatoWebApp.model;

/**
 * Represents an item in the shopping cart.
 */
public class CartItem {

    // Unique identifier for the cart item
    private Integer id;

    // Name of the product
    private String name;

    // Description of the product
    private String description;

    // Price of the product
    private Float price;

    // Quantity of the product in the cart
    private Integer qty;

    // Total price for this cart item (price * qty)
    private Float total;

    /**
     * Constructor to initialize the cart item.
     *
     * @param id          The unique identifier for the cart item.
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param price       The price of the product.
     * @param qty         The quantity of the product in the cart.
     */
    public CartItem(Integer id, String name, String description, Float price, Integer qty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
        updateTotal();
    }

    // Standard getters and setters with added documentation

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
        updateTotal();
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
        updateTotal();
    }

    public Float getTotal() {
        return total;
    }

    /**
     * Updates the total price for this cart item.
     */
    private void updateTotal() {
        this.total = this.price * this.qty;
    }

    @Override
    public String toString() {
        return this.id + ";" + this.name + ";" + this.description + ";" + this.price + ";" + this.qty;
    }
}
