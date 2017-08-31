package com.anythingintellect.happyshop.viewmodel;

import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.util.Navigator;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CategoryItemViewModel {

    private Category category;
    private final Navigator navigator;

    public CategoryItemViewModel(Navigator navigator) {
        this.navigator = navigator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
