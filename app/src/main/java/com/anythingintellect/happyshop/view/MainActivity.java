package com.anythingintellect.happyshop.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.anythingintellect.happyshop.HappyShopApp;
import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.di.ContextModule;
import com.anythingintellect.happyshop.repo.CartRepository;
import com.anythingintellect.happyshop.repo.CartRepositoryImpl;
import com.anythingintellect.happyshop.util.Navigator;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    CartRepository cartRepository;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolveDependency();
        if (savedInstanceState == null) {
            navigator.openCategoryList();
        }
    }

    private void resolveDependency() {
        ((HappyShopApp)getApplication())
                .getAppComponent()
                .plusContextModule(new ContextModule(this))
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem cartItem = menu.getItem(0);
        ((TextView)cartItem.getActionView()
                .findViewById(R.id.txtCartQuantity))
                .setText(String.valueOf(cartRepository.getCartCount()));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
