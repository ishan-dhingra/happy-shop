package com.anythingintellect.happyshop.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.happyshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseFragment {

    public static final String KEY_CATEGORY = "category";
    private String category = "";

    public ProductListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString(KEY_CATEGORY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvList = view.findViewById(R.id.rvList);
        setUpRv(rvList);
    }

    private void setUpRv(RecyclerView rvList) {
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public String getTitle() {
        return category;
    }
}
