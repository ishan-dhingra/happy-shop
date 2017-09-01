package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public interface CatalogRepository {
    List<Category> getCategories();

    RealmResults<Product> getProductsByCategory(String category);

    RealmResults<Product> getProductId(long id);

    Observable<List<Product>> fetchAndPersistProducts(int page, String category);

    Observable<Product> fetchAndPersistProduct(long prodId);
}
