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
}
