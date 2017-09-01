package com.anythingintellect.happyshop.viewmodel;

import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.util.Navigator;

/**
 * Created by ishan.dhingra on 01/09/17.
 */

public class ProductItemViewModel  {

    private Product product;
    private Navigator navigator;

    public ProductItemViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPrice() {
        return "$" + product.getPrice();
    }

    public void onClick() {
        navigator.openProductDetails(product.getId());
    }

}
