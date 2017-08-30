package com.anythingintellect.happyshop.db;

import com.anythingintellect.happyshop.model.CartEntry;
import com.anythingintellect.happyshop.model.Product;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public interface LocalDataStore {

    // For product list page
    RealmResults<Product> getProductByCategory(String category);

    void saveProducts(List<Product> products);

    // For product detail page
    RealmResults<Product> getProductById(long id);

    RealmResults<CartEntry> getCartEntryByProduct(long id);

    void addToCart(long id);

}
