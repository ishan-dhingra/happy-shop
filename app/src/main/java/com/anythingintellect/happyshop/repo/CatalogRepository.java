package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.network.HappyShopAPIService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CatalogRepository {

    private final LocalDataStore localStore;
    private final HappyShopAPIService apiService;
    private static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("Skincare", R.mipmap.ic_launcher));
        categories.add(new Category("Tools", R.mipmap.ic_launcher));
        categories.add(new Category("Nails", R.mipmap.ic_launcher));
        categories.add(new Category("Makeup", R.mipmap.ic_launcher));
        categories.add(new Category("Men", R.mipmap.ic_launcher));
    }


    public CatalogRepository(LocalDataStore localStore, HappyShopAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
