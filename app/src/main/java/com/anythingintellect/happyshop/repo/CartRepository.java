package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.db.LocalDataStore;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CartRepository {

    private final LocalDataStore localStore;

    public CartRepository(LocalDataStore localStore) {
        this.localStore = localStore;
    }

    public void addToCart(long productId) {
        localStore.addToCart(productId);
    }
}
