package com.anythingintellect.happyshop.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.anythingintellect.happyshop.db.LocalDataStore;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.repo.CatalogRepository;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CategoryListViewModel {

    private final CatalogRepository repository;
    private final ObservableList<Category> categories;

    public CategoryListViewModel(CatalogRepository catalogRepository) {
        this.repository = catalogRepository;
        this.categories = new ObservableArrayList<>();
    }

    public void loadCategories() {
        categories.addAll(repository.getCategories());
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }
}
