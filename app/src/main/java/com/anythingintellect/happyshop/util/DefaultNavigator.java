package com.anythingintellect.happyshop.util;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.view.CategoryListFragment;

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
        fragmentManager.beginTransaction()
                .add(R.id.container, categoryListFragment)
                .addToBackStack(categoryListFragment.getClass().getName())
                .commit();
    }

    @Override
    public void openProductList(String category) {

    }

    @Override
    public void openProductDetails(long productId) {

    }
}
