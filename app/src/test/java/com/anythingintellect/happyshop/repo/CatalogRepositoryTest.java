package com.anythingintellect.happyshop.repo;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.network.HappyShopAPIService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CatalogRepositoryTest {

    private CatalogRepository catalogRepository;
    @Mock
    private LocalDataStore localStore;
    @Mock
    private HappyShopAPIService apiService;

    @Before
    public void setup() {
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

    // getProductById
    // Should return from localStore and hit the api


}
