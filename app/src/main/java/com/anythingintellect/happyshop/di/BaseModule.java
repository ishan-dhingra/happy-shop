package com.anythingintellect.happyshop.di;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.network.HappyShopAPIService;
import com.anythingintellect.happyshop.repo.CartRepository;
import com.anythingintellect.happyshop.repo.CartRepositoryImpl;
import com.anythingintellect.happyshop.repo.CatalogRepository;
import com.anythingintellect.happyshop.repo.CatalogRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ishan.dhingra on 30/08/17.
 */
@Module(includes = {NetworkModule.class, PersistenceModule.class})
public class BaseModule {

    @Provides
    @Singleton
    public CatalogRepository providesCatalogRepository(LocalDataStore localDataStore,
                                                       HappyShopAPIService apiService) {
        return new CatalogRepositoryImpl(localDataStore, apiService);
    }

    @Provides
    @Singleton
    public CartRepository providesCartRepository(LocalDataStore localDataStore) {
        return new CartRepositoryImpl(localDataStore);
    }

}
