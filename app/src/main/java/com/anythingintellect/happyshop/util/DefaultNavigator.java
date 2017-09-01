package com.anythingintellect.happyshop.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.view.CategoryListFragment;
import com.anythingintellect.happyshop.view.ProductListFragment;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class DefaultNavigator implements Navigator {

    private final Context context;
    private final FragmentManager fragmentManager;

    public DefaultNavigator(Context context) {
        this.context = context;
        this.fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
    }


    @Override
    public void openCategoryList() {
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        addToBackStack(categoryListFragment);
    }

    private void addToBackStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    @Override
    public void openProductList(String category) {
        ProductListFragment productListFragment = new ProductListFragment();
        Bundle argument = new Bundle();
        argument.putString(ProductListFragment.KEY_CATEGORY, category);
        productListFragment.setArguments(argument);
        addToBackStack(productListFragment);

    }

    @Override
    public void openProductDetails(long productId) {

    }
}
