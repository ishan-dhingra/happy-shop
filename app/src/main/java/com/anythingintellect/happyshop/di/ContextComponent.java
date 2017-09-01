package com.anythingintellect.happyshop.di;

import com.anythingintellect.happyshop.view.BaseFragment;
import com.anythingintellect.happyshop.view.CategoryListFragment;
import com.anythingintellect.happyshop.view.MainActivity;
import com.anythingintellect.happyshop.view.ProductListFragment;

import dagger.Subcomponent;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

@Subcomponent(modules = ContextModule.class)
@PerContext
public interface ContextComponent {

    void inject(MainActivity mainActivity);
    void inject(CategoryListFragment fragment);
    void inject(ProductListFragment fragment);

}
