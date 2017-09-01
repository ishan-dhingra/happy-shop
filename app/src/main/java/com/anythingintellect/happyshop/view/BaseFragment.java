package com.anythingintellect.happyshop.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.anythingintellect.happyshop.HappyShopApp;
import com.anythingintellect.happyshop.R;
import com.anythingintellect.happyshop.di.ContextModule;

/**
 * Created by ishan.dhingra on 01/09/17.
 */

public class BaseFragment extends Fragment {

    
    public String getTitle() {
        return getString(R.string.app_name);
    }

}
