package com.gcu.CouchPotatoWebApp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of products. This class is designed to be serialized to/from XML.
 */
@XmlRootElement(name="products")
public class ProductList {

    // List of products
    private List<ProductModel> productList = new ArrayList<>();

    /**
     * Gets the list of products.
     *
     * @return A list of ProductModel objects.
     */
    @XmlElement(name="product")
    public List<ProductModel> getProductList() {
        return productList;
    }

    /**
     * Sets the list of products.
     *
     * @param products A list of ProductModel objects.
     */
    public void setProductList(List<ProductModel> products) {
        this.productList = products;
    }
}
