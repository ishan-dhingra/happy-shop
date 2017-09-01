package com.anythingintellect.happyshop.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.anythingintellect.happyshop.view.ProductDetailActivity;

/**
 * Created by ishan.dhingra on 02/09/17.
 */

public class DefaultToaster implements Toaster {

    private final Context context;

    public DefaultToaster(Context context) {
        this.context = context;
    }


    @Override
    public void showLongMessage(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showShortMessage(String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
