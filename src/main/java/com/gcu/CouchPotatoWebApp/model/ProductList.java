package com.gcu.CouchPotatoWebApp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="products")
public class ProductList
{
    private List<ProductModel> productList = new ArrayList<ProductModel>();

    @XmlElement(name="product")
    public List<ProductModel> getProductList()
    {
        return productList;
    }

    public void setProductList(List<ProductModel> products)
    {
        this.productList = products;
    }
}

