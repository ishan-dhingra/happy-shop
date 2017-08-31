package com.anythingintellect.happyshop.di;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.db.RealmLocalStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Module
public class PersistenceModule {

    private final boolean isInMemory;

    public PersistenceModule(boolean isInMemory) {
        this.isInMemory = isInMemory;
    }

    @Provides
    @Singleton
    public RealmConfiguration providesRealmConfiguration() {
        RealmConfiguration.Builder builder = new RealmConfiguration
                .Builder();
        builder.deleteRealmIfMigrationNeeded();
        if (isInMemory) {
            builder.inMemory();
        }
        return builder.build();
    }

    @Provides
    public Realm providesRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }

    @Provides
    public LocalDataStore providesLocalDataStore(Realm realm) {
        return new RealmLocalStore(realm);
    }

}
