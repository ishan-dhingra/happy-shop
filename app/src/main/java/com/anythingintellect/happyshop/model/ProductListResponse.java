package com.anythingintellect.happyshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductListResponse {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
