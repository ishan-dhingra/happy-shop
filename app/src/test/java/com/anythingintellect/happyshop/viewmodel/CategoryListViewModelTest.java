package com.anythingintellect.happyshop.viewmodel;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.repo.CatalogRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CategoryListViewModelTest {

    @Mock
    private CatalogRepository catalogRepository;
    private CategoryListViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        viewModel = new CategoryListViewModel(catalogRepository);
    }

    // Should load categories from repository
    @Test
    public void testLoadCategories_ShouldLoadFromLocalStore() {
        viewModel.loadCategories();
        verify(catalogRepository).getCategories();
    }

}
