package com.anythingintellect.happyshop.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ishan.dhingra on 31/08/17.
 */

public class BindingUtils {

    @BindingAdapter("bind:src")
    public static void bindImageSrc(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
    @BindingAdapter("bind:drawableTop")
    public static void bindDrawableTop(TextView textView, int resource) {
        textView.setCompoundDrawablesWithIntrinsicBounds(0, resource, 0, 0);
    }

}
