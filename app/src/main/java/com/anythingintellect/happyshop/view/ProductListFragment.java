package com.anythingintellect.happyshop.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anythingintellect.happyshop.HappyShopApp;
import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.adapter.ProductAdapter;
import com.anythingintellect.happyshop.databinding.FragmentProductListBinding;
import com.anythingintellect.happyshop.di.ContextModule;
import com.anythingintellect.happyshop.model.Product;
import com.anythingintellect.happyshop.repo.CatalogRepository;
import com.anythingintellect.happyshop.util.Navigator;
import com.anythingintellect.happyshop.viewmodel.ProductListViewModel;

import javax.inject.Inject;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseFragment {

    public static final String KEY_CATEGORY = "category";
    private String category = "";

    @Inject
    CatalogRepository repository;
    @Inject
    Navigator navigator;
    private ProductListViewModel viewModel;
    private ProductAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public ProductListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString(KEY_CATEGORY);
        inject();
        viewModel = new ProductListViewModel(repository, category);
        viewModel.getProducts().addChangeListener(changeListener);
        adapter = new ProductAdapter(viewModel.getProducts(), navigator);
        viewModel.loadForPage(1);
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
        FragmentProductListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_list, container, false);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvList = view.findViewById(R.id.rvList);
        setUpRv(rvList);
    }

    private void setUpRv(RecyclerView rvList) {
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(adapter);
        rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!viewModel.hasMore()) {
                    return;
                }
                int currentPage = (linearLayoutManager.findLastCompletelyVisibleItemPosition()+1)/10;
                if (currentPage >= viewModel.getPage()) {
                    viewModel.loadForPage(currentPage + 1);
                }

            }
        });
    }

    @Override
    public String getTitle() {
        return category;
    }

    private final OrderedRealmCollectionChangeListener<RealmResults<Product>> changeListener =
            new OrderedRealmCollectionChangeListener<RealmResults<Product>>() {
                @Override
                public void onChange(RealmResults<Product> collection, OrderedCollectionChangeSet changeSet) {
                    // `null`  means the async query returns the first time.
                    if (changeSet == null) {
                        adapter.notifyDataSetChanged();
                        return;
                    }
                    // For deletions, the adapter has to be notified in reverse order.
                    OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
                    for (int i = deletions.length - 1; i >= 0; i--) {
                        OrderedCollectionChangeSet.Range range = deletions[i];
                        adapter.notifyItemRangeRemoved(range.startIndex, range.length);
                    }

                    OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
                    for (OrderedCollectionChangeSet.Range range : insertions) {
                        adapter.notifyItemRangeInserted(range.startIndex, range.length);
                    }

                    OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
                    for (OrderedCollectionChangeSet.Range range : modifications) {
                        adapter.notifyItemRangeChanged(range.startIndex, range.length);
                    }

                }
            };

}
