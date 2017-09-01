package com.anythingintellect.happyshop.viewmodel;

import android.databinding.ObservableField;

import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.repo.CatalogRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 01/09/17.
 */

public class ProductListViewModel {

    private final ObservableField<Boolean> showError;
    private final ObservableField<Boolean> isLoading;
    private final RealmResults<Product> products;
    private final String category;
    private int page = 0;
    private boolean hasMore = true;

    private CatalogRepository catalogRepository;

    public ProductListViewModel(CatalogRepository catalogRepository, String category) {
        this.catalogRepository = catalogRepository;
        this.category = category;
        this.products = catalogRepository.getProductsByCategory(category);
        this.showError = new ObservableField<>(false);
        this.isLoading = new ObservableField<>(false);
    }

    public ObservableField<Boolean> getShowError() {
        return showError;
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public RealmResults<Product> getProducts() {
        return products;
    }

    public void loadForPage(final int requestedPage) {
        if (!isLoading.get()) {
            isLoading.set(true);
            showError.set(false);
            catalogRepository.fetchAndPersistProducts(requestedPage, category).subscribe(new Observer<List<Product>>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull List<Product> products) {
                    hasMore = products.size() == 10;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    isLoading.set(false);
                    if (products.size() == 0) {
                        showError.set(true);
                    }
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {
                    isLoading.set(false);
                    page = requestedPage;
                }
            });
        }
    }

    public int getPage() {
        return page;
    }

    public boolean hasMore() {
        return hasMore;
    }

}
