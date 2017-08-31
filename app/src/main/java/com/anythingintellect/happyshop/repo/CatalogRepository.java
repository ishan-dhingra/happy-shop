package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.model.ProductListResponse;
import com.anythingintellect.happyshop.network.HappyShopAPIService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

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

    public RealmResults<Product> getProductsByCategory(String category) {
        return localStore.getProductByCategory(category);
    }

    public RealmResults<Product> getProductId(long id) {
        return localStore.getProductById(id);
    }

    public void fetchAndPersistProducts(int page, String category) {
        apiService.getProductList(page, category)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ProductListResponse, List<Product>>() {
                    @Override
                    public List<Product> apply(@NonNull ProductListResponse productListResponse) throws Exception {
                        return productListResponse.getProducts();
                    }
                }).subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Product> products) {
                        localStore.saveProducts(products);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void fetchAndPersistProduct(long prodId) {

    }
}
