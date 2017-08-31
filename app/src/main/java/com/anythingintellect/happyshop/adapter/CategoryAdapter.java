package com.anythingintellect.happyshop.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.databinding.ItemCategoryBinding;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.viewmodel.CategoryItemViewModel;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final ObservableList<Category> categoryList;
    private final Navigator navigator;

    public CategoryAdapter(ObservableList<Category> categoryList, Navigator navigator) {
        this.categoryList = categoryList;
        this.navigator = navigator;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_category, parent, false);
        return new CategoryViewHolder(binding, new CategoryItemViewModel(navigator));
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final CategoryItemViewModel viewModel;
        private final ItemCategoryBinding binding;
        public CategoryViewHolder(ItemCategoryBinding binding,
                                  CategoryItemViewModel viewModel) {
            super(binding.getRoot());
            this.viewModel = viewModel;
            this.binding = binding;
        }

        public void bind(Category category) {
            viewModel.setCategory(category);
            binding.setVm(viewModel);
            binding.executePendingBindings();
        }
    }

}
