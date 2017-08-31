package com.anythingintellect.happyshop.db;

import android.app.Instrumentation;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.util.MockData;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ishan.dhingra on 30/08/17.
 */

@RunWith(AndroidJUnit4.class)
public class RealmLocalStoreTest {

    private Realm realm;
    private RealmLocalStore localStore;

    @Before
    public void setup() {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
                builder.inMemory();
                realm = Realm.getInstance(builder.build());
                localStore = new RealmLocalStore(realm);
            }
        });

        MockData.init();
    }

    public void runOnMainThread(Runnable runnable) {
        InstrumentationRegistry.getInstrumentation()
                .runOnMainSync(runnable);
    }

    @After
    public void tearDown() {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                realm.close();
            }
        });
    }

    // Should get product by category
    // Given db with category should return
    @Test
    @UiThreadTest
    public void testGetProductByCategory_shouldReturnAllProductForGivenCategoryOnly() {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                final List<Product> productList = MockData.getProductList();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(productList);
                    }
                });
                String category = MockData.getCategory();
                int expectedCount = 0;
                for (Product product : productList) {
                    if (product.getCategory().equals(category)) {
                        ++expectedCount;
                    }
                }
                RealmResults<Product> result = localStore.getProductByCategory(category);
                assertNotEquals(null, result);
                result.load();
                assertEquals(expectedCount, result.size());
                for (Product product : result) {
                    assertEquals(category, product.getCategory());
                }
            }
        });


    }

    // Should save product list
    @Test
    @UiThreadTest
    public void testSaveProducts_shouldSaveProducts() {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                List<Product> productList = MockData.getProductList();
                localStore.saveProducts(productList);
                RealmResults<Product> results = realm.where(Product.class).findAllAsync();
                results.load();
                assertNotEquals(null, results);
            }
        });
    }

    // Should get product by id

    // Should get cart entry by product id

    // Should add to cart


}
