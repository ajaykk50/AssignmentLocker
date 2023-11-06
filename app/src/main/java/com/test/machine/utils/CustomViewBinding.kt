package com.test.machine.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:fromBackgroundResource")
fun loadFromImageFromResource(imageView: ImageView, imageResource: Int) {
    imageView.setBackgroundResource(imageResource)
}

@BindingAdapter("app:toBackgroundResource")
fun loadToImageFromResource(imageView: ImageView, imageResource: Int) {
    imageView.setBackgroundResource(imageResource)
}