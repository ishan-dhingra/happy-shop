package com.anythingintellect.happyshop.db;

import android.support.test.runner.AndroidJUnit4;

import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.util.MockData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.inMemory();
        realm = Realm.getInstance(builder.build());
        localStore = new RealmLocalStore(realm);
        MockData.init();
    }

    @After
    public void tearDown() {
        realm.close();
    }

    // Should get product by category
    // Given db with category should return
    @Test
    public void testGetProductByCategory_shouldReturnAllProductForGivenCategoryOnly() {
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
        assertEquals(expectedCount, result.size());
        for (Product product : result) {
            assertEquals(category, product.getCategory());
        }
    }

    // Should save product list

    // Should get product by id

    // Should get cart entry by product id

    // Should add to cart


}
