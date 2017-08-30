package com.anythingintellect.happyshop;

import android.app.Application;

import com.anythingintellect.happyshop.network.AppComponent;
import com.anythingintellect.happyshop.network.DaggerAppComponent;
import com.anythingintellect.happyshop.network.NetworkModule;
import com.anythingintellect.happyshop.util.Constant;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class HappyShopApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
