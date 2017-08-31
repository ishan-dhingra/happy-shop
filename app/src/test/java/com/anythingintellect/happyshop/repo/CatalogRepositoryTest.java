package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.model.ProductListResponse;
import com.anythingintellect.happyshop.network.HappyShopAPIService;
import com.anythingintellect.happyshop.util.MockData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmResults;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class CatalogRepositoryTest {

    private CatalogRepository catalogRepository;
    @Mock
    private LocalDataStore localStore;
    @Mock
    private HappyShopAPIService apiService;

    @Before
    public void setup() {
        MockData.init();
        MockitoAnnotations.initMocks(this);
        catalogRepository = new CatalogRepository(localStore, apiService);
    }


    // getCategories
    // Should return categories
    @Test
    public void testGetCategories_shouldReturnNonNullCategories() {
        List<Category> categories = catalogRepository.getCategories();
        assertNotEquals(null, categories);
    }
    // getProductByCategory
    // Should return from localStore and hit the api
    @Test
    public void testGetProductsByCategory_ShouldReturnFromDB() {
        // TODO: Find out a way to return empty realm result
        String category = MockData.getCategory();
        RealmResults<Product> results = catalogRepository
                .getProductsByCategory(category);
        verify(localStore).getProductByCategory(category);
    }


    // getProductById
    // Should return from localStore and hit the api
    @Test
    public void testGetProductById_ShouldReturnFromDB() {
        long prodId = MockData.getProduct().getId();
        RealmResults<Product> productResult = catalogRepository
                .getProductId(prodId);
        verify(localStore).getProductById(prodId);
    }


}
