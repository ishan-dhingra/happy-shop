package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

interface CatalogRepository {
    List<Category> getCategories();

    RealmResults<Product> getProductsByCategory(String category);

    RealmResults<Product> getProductId(long id);

    void fetchAndPersistProducts(int page, String category);

    void fetchAndPersistProduct(long prodId);
}
