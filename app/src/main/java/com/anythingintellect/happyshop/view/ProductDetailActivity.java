package com.anythingintellect.happyshop.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.anythingintellect.happyshop.HappyShopApp;
import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.databinding.ActivityProductDetailBinding;
import com.anythingintellect.happyshop.di.ContextModule;
import com.anythingintellect.happyshop.repo.CartRepository;
import com.anythingintellect.happyshop.repo.CatalogRepository;
import com.anythingintellect.happyshop.util.Toaster;
import com.anythingintellect.happyshop.viewmodel.ProductDetailsViewModel;

import javax.inject.Inject;

public class ProductDetailActivity extends AppCompatActivity {

    public static final String KEY_PRODUCT_ID = "productId";

    @Inject
    CatalogRepository catalogRepository;
    @Inject
    CartRepository cartRepository;
    @Inject
    Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        long productId = getIntent().getLongExtra(KEY_PRODUCT_ID, 0);
        ProductDetailsViewModel viewModel = new ProductDetailsViewModel(catalogRepository, cartRepository, productId, toaster);
        ActivityProductDetailBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_product_detail);
        binding.setVm(viewModel);
        viewModel.loadProduct();
        setupToolBar();
    }

    private void inject() {
        ((HappyShopApp)getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(this))
                .inject(this);
    }

    private void setupToolBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
                } else if (verticalOffset == 0) {
                    // Expanded
                    getSupportActionBar().setHomeAsUpIndicator(null);
                } else if (appBarLayout.getTotalScrollRange() - Math.abs(verticalOffset) >= 50) {
                    getSupportActionBar().setHomeAsUpIndicator(null);
                }
            }
        });
    }

}
