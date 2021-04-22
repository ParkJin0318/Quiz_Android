package com.parkjin.quiz.util;

import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {

    @BindingAdapter("android:uri")
    public static void setImageUri(ImageView view, String str) {
        if (str != null && !str.isEmpty()) {
            Uri uri = Uri.parse(str);
            if (uri != null) view.setImageURI(uri);
        }
    }
}