package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.CartEntry;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CartRepositoryImpl implements CartRepository {

    private final LocalDataStore localStore;

    public CartRepositoryImpl(LocalDataStore localStore) {
        this.localStore = localStore;
    }

    @Override
    public void addToCart(long productId) {
        localStore.addToCart(productId);
    }

    @Override
    public RealmResults<CartEntry> getCartEntry(long productId) {
        return localStore.getCartEntryByProduct(productId);
    }
}
