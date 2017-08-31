package com.anythingintellect.happyshop.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class Product extends RealmObject {

    @PrimaryKey
    private long id;
    private String name;
    private String category;
    private double price;
    // img_url
    private String imgUrl;
    // under_sale
    private boolean underSale;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isUnderSale() {
        return underSale;
    }

    public void setUnderSale(boolean underSale) {
        this.underSale = underSale;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Product) {
            return ((Product)obj).getId() == id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (id % Integer.MAX_VALUE);
    }
}
