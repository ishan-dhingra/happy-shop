package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.model.ProductListResponse;
import com.anythingintellect.happyshop.model.ProductResponse;
import com.anythingintellect.happyshop.network.HappyShopAPIService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CatalogRepositoryImpl implements CatalogRepository {

    private final LocalDataStore localStore;
    private final HappyShopAPIService apiService;
    private static List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("Skincare", R.drawable.ic_skincare));
        categories.add(new Category("Tools", R.drawable.ic_tools));
        categories.add(new Category("Nails", R.drawable.ic_nail));
        categories.add(new Category("Makeup", R.drawable.ic_makeup));
        categories.add(new Category("Men", R.drawable.ic_men));
        categories.add(new Category("Bath & Body", R.drawable.ic_bath));
    }


    public CatalogRepositoryImpl(LocalDataStore localStore, HappyShopAPIService apiService) {
        this.localStore = localStore;
        this.apiService = apiService;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public RealmResults<Product> getProductsByCategory(String category) {
        return localStore.getProductByCategory(category);
    }

    @Override
    public RealmResults<Product> getProductId(long id) {
        return localStore.getProductById(id);
    }

    @Override
    public Observable<List<Product>> fetchAndPersistProducts(int page, String category) {
        return apiService.getProductList(page, category)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ProductListResponse, List<Product>>() {
                    @Override
                    public List<Product> apply(@NonNull ProductListResponse productListResponse) throws Exception {
                        return productListResponse.getProducts();
                    }
                })
                .doOnNext(new Consumer<List<Product>>() {
                    @Override
                    public void accept(@NonNull List<Product> products) throws Exception {
                        localStore.saveProducts(products);
                    }
                });
    }

    @Override
    public Observable<Product> fetchAndPersistProduct(final long prodId) {
        return apiService.getProduct(prodId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ProductResponse, Product>() {
                    @Override
                    public Product apply(@NonNull ProductResponse productResponse) throws Exception {
                        return productResponse.getProduct();
                    }
                })
                .doOnNext(new Consumer<Product>() {
                    @Override
                    public void accept(@NonNull Product product) throws Exception {
                        localStore.saveProducts(Collections.singletonList(product));
                    }
                });

    }
}
