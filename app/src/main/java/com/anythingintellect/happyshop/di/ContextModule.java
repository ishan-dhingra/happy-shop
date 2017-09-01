package com.anythingintellect.happyshop.di;

import android.content.Context;


import com.anythingintellect.happyshop.util.DefaultNavigator;
import com.anythingintellect.happyshop.util.DefaultToaster;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.util.Toaster;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@PerContext
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerContext
    public Navigator providesNavigator() {
        return new DefaultNavigator(context);
    }

    @Provides
    @PerContext
    public Toaster providesToaster() {
        return new DefaultToaster(context);
    }
}
