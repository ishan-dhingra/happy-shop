package com.anythingintellect.happyshop.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class CartEntry extends RealmObject {

    @PrimaryKey
    private long productId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CartEntry) {
            return ((CartEntry)obj).getProductId() == getProductId();
        }
        return false;
    }
}
