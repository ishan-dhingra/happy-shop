package com.anythingintellect.happyshop.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;

import com.anythingintellect.happyshop.model.CartEntry;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.repo.CartRepository;
import com.anythingintellect.happyshop.repo.CatalogRepository;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.util.Toaster;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 01/09/17.
 */

public class ProductDetailsViewModel {

    private final ObservableField<Product> product;
    private final ObservableField<Boolean> isLoading;
    private final ObservableField<Boolean> showError;
    private final CatalogRepository repository;
    private final CartRepository cartRepository;
    private final long productId;
    private final ObservableField<Boolean> inCart;
    private final Toaster toaster;
    private final RealmResults<Product> realProduct;
    private final RealmResults<CartEntry> realCart;


    public ProductDetailsViewModel(CatalogRepository repository, CartRepository cartRepository, long productId, Toaster toaster) {
        this.repository = repository;
        this.cartRepository = cartRepository;
        this.productId = productId;
        this.product = new ObservableField<>();
        this.showError = new ObservableField<>(false);
        this.isLoading = new ObservableField<>(false);
        this.inCart = new ObservableField<>(false);
        this.realProduct = repository.getProductId(productId);
        this.realCart = cartRepository.getCartEntry(productId);
        this.toaster = toaster;
        realProduct.addChangeListener(productChangeListener);
        realCart.addChangeListener(cartChangeListener);
    }

    public ObservableField<Product> getProduct() {
        return product;
    }

    public ObservableField<Boolean> getInCart() {
        return inCart;
    }

    public String getPrice() {
        return product.get() == null ? "" : "$" + product.get().getPrice();
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public ObservableField<Boolean> getShowError() {
        return showError;
    }

    public void loadProduct() {
        if (isLoading.get()) {
            return;
        }
        showError.set(false);
        isLoading.set(true);
        repository.fetchAndPersistProduct(productId).subscribe(new Observer<Product>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Product freshProduct) {
            }

            @Override
            public void onError(@NonNull Throwable e) {
                isLoading.set(false);
                toaster.showLongMessage("Unable to sync with server!");
                showError.set(true);
            }

            @Override
            public void onComplete() {
                isLoading.set(false);
            }
        });
    }


    public void onAddToCartClick() {
        if (!inCart.get()) {
            cartRepository.addToCart(productId);
            toaster.showShortMessage("Added to cart!");
        } else {
            cartRepository.removeFromCart(productId);
            toaster.showShortMessage("Removed from cart!");
        }
    }
    private RealmChangeListener<RealmResults<Product>> productChangeListener = new RealmChangeListener<RealmResults<Product>>() {
        @Override
        public void onChange(RealmResults<Product> results) {
            product.set(realProduct.get(0));
        }
    };

    private RealmChangeListener<RealmResults<CartEntry>> cartChangeListener = new RealmChangeListener<RealmResults<CartEntry>>() {
        @Override
        public void onChange(RealmResults<CartEntry> cartEntries) {
            inCart.set(realCart.size() != 0);
        }
    };

}
