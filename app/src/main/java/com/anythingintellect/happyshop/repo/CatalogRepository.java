package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.network.HappyShopAPIService;

import java.util.List;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CatalogRepository {

    private final LocalDataStore localStore;
    private final HappyShopAPIService apiService;
    private List<Category> categories;


    public CatalogRepository(LocalDataStore localStore, HappyShopAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }

    public List<Category> getCategories() {
        return null;
    }
}
