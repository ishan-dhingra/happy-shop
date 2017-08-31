package com.anythingintellect.happyshop;

import android.app.Application;

import com.anythingintellect.happyshop.di.AppComponent;
import com.anythingintellect.happyshop.di.DaggerAppComponent;
import com.anythingintellect.happyshop.di.NetworkModule;
import com.anythingintellect.happyshop.di.PersistenceModule;
import com.anythingintellect.happyshop.util.Constant;

import io.realm.Realm;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

public class HappyShopApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .persistenceModule(new PersistenceModule(false))
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
