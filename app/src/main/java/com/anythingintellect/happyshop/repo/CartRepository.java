package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.model.CartEntry;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public interface CartRepository {
    void addToCart(long productId);

    RealmResults<CartEntry> getCartEntry(long productId);

    void removeFromCart(long productId);

    long getCartCount();
}
