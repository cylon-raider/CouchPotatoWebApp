package com.gcu.CouchPotatoWebApp.model;

public class CartItem
{
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private Integer qty;
    private Float total;

    public CartItem(Integer id, String name, String description, Float price, Integer qty) {
        this.setId(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
        setTotal();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Float getPrice()
    {
        return price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
        setTotal();
    }

    public Integer getQty()
    {
        return qty;
    }

    public void setQty(Integer qty)
    {
        this.qty = qty;
        setTotal();
    }

    public Float getTotal()
    {
        return total;
    }

    private void setTotal()
    {
        this.total = this.price * this.qty;
    }

    @Override
    public String toString()
    {
        return this.id + ";" + this.name + ";" + this.description + ";" + String.valueOf(this.price) + ";" + String.valueOf(this.qty);
    }
}
