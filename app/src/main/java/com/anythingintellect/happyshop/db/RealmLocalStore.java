package com.anythingintellect.happyshop.db;

import com.anythingintellect.happyshop.model.CartEntry;
import com.anythingintellect.happyshop.model.Product;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class RealmLocalStore implements LocalDataStore {

    // TODO: Think about closing
    private final Realm realm;

    public RealmLocalStore(Realm realm) {
        this.realm = realm;
    }


    @Override
    public RealmResults<Product> getProductByCategory(String category) {
        return realm.where(Product.class)
                .equalTo("category", category).
                        findAllSortedAsync("id", Sort.ASCENDING);
    }

    @Override
    public void saveProducts(List<Product> products) {

    }

    @Override
    public RealmResults<Product> getProductById(long id) {
        return null;
    }

    @Override
    public RealmResults<CartEntry> getCartEntryByProduct(long id) {
        return null;
    }

    @Override
    public void addToCart(long id) {

    }
}
