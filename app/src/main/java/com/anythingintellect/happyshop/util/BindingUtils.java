package com.anythingintellect.happyshop.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

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

    @BindingAdapter("bind:visible")
    public static void bindVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:imgUrl")
    public static void bindImageUrl(SimpleDraweeView draweeView, String imgUrl) {
        Uri imgUri = Uri.parse(imgUrl);
        draweeView.setImageURI(imgUri);
    }

}
