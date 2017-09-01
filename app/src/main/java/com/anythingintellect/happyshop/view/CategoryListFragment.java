package com.anythingintellect.happyshop.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.happyshop.HappyShopApp;
import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.adapter.CategoryAdapter;
import com.anythingintellect.happyshop.di.ContextModule;
import com.anythingintellect.happyshop.model.Category;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.viewmodel.CategoryListViewModel;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends BaseFragment {

    @Inject
    CategoryListViewModel viewModel;
    @Inject
    Navigator navigator;
    private CategoryAdapter categoryAdapter;


    public CategoryListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void inject() {
        ((HappyShopApp)getActivity()
                .getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(getActivity()))
                .inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inject();
        viewModel.loadCategories();
        categoryAdapter = new CategoryAdapter(viewModel.getCategories(), navigator);
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvList = view.findViewById(R.id.rvList);
        setupRv(rvList);
    }

    private void setupRv(RecyclerView rvList) {
        rvList.setAdapter(categoryAdapter);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        rvList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));
    }


}
