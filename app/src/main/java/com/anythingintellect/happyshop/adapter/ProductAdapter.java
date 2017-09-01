package com.anythingintellect.happyshop.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.databinding.ItemProductBinding;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.viewmodel.ProductItemViewModel;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 01/09/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final RealmResults<Product> products;
    private final Navigator navigator;

    public ProductAdapter(RealmResults<Product> products, Navigator navigator) {
        this.products = products;
        this.navigator = navigator;
        setHasStableIds(true);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_product, parent, false);
        return new ProductViewHolder(binding, new ProductItemViewModel(navigator));
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ItemProductBinding binding;
        private final ProductItemViewModel viewModel;

        ProductViewHolder(ItemProductBinding binding, ProductItemViewModel viewModel) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void bind(Product product) {
            viewModel.setProduct(product);
            binding.setVm(viewModel);
            binding.executePendingBindings();

        }
    }
}
