package com.anythingintellect.happyshop.model;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class Category {

    private final String name;
    private final int icon;

    public Category(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
