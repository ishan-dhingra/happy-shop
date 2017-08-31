package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.BaseTest;
import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.model.ProductListResponse;
import com.anythingintellect.happyshop.model.ProductResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class CatalogRepositoryTest extends BaseTest {

    private CatalogRepositoryImpl catalogRepository;
    @Mock
    private LocalDataStore localStore;
    @Mock
    private HappyShopAPIService apiService;

    @Before
    public void setup() {
        MockData.init();
        MockitoAnnotations.initMocks(this);
        catalogRepository = new CatalogRepositoryImpl(localStore, apiService);
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

    // fetchAndPersistForPageAndCategory
    // Should fetch from api and save in local store
    @Test
    public void testFetchAndPersistForPageAndCategory_ShouldCallAPIAndSaveInLocalStore() {
        ProductListResponse productListResponse = MockData.getProductListResponse();
        String category = MockData.getCategory();
        int page = 1;
        when(apiService.getProductList(page, category)).thenReturn(Observable.just(productListResponse));

        catalogRepository.fetchAndPersistProducts(page, category);
        // TODO: Find way to execute observable immediately
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verify(apiService).getProductList(page, category);
        verify(localStore).saveProducts(productListResponse.getProducts());

    }

    // fetchAndPersistForProductId
    // Should fetch single product from api and persist
    @Test
    public void testFetchAndPersistForProductId_ShouldCallAPIAndSaveInLocalStore() {
        Product product = MockData.getProduct();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProduct(product);
        long prodId = product.getId();
        when(apiService.getProduct(prodId)).thenReturn(Observable.just(productResponse));

        catalogRepository.fetchAndPersistProduct(prodId);

        verify(apiService).getProduct(prodId);
        verify(localStore).saveProducts(any(List.class));

    }


}
