package com.anythingintellect.happyshop.network;

import android.content.Context;


import dagger.Module;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@PerFragment
@Module
public class FragmentModule {

    private final Context context;

    public FragmentModule(Context context) {
        this.context = context;
    }
}
