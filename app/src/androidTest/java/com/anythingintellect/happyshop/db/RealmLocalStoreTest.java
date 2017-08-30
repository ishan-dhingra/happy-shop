package com.anythingintellect.happyshop.db;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class RealmLocalStoreTest {

    private Realm realm;
    private RealmLocalStore localStore;

    @Before
    public void setup() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.inMemory();
        realm = Realm.getInstance(builder.build());
        localStore = new RealmLocalStore(realm);
    }


}
