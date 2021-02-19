package com.hehebaba.practise.architecture.databinding

import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object BindingAdapters {

    @BindingAdapter(value = ["android:paddingStart"])
    @JvmStatic
    fun setPaddingLeft(view: View, padding: Int) {
        view.setPadding(padding, view.paddingTop, view.paddingRight, view.paddingBottom)
    }

    @BindingAdapter(value = ["android:paddingStart", "android:paddingLeft"], requireAll = false)
    @JvmStatic
    fun setPaddingStartOrLeft(view: View, paddingLeft: Int?, paddingStart: Int?) {
        if (null != paddingStart) {
            view.setPadding(paddingStart, view.paddingTop, view.paddingRight, view.paddingBottom)
            return
        }
        if (null != paddingLeft) {
            view.setPadding(paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)
        }
    }

    @BindingAdapter("imageUrl", "error")
    @JvmStatic
    fun loadImage(view: ImageView, url: String, error: Drawable) {
        Picasso.get().load(url).error(error).into(view)
    }

    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
        if (url == null) {
            imageView.setImageDrawable(placeHolder)
        } else {
            if (null == placeHolder) {
                Picasso.get().load(url).into(imageView)
            } else {
                Picasso.get().load(url).placeholder(placeHolder).into(imageView)
            }
        }
    }

    @BindingAdapter("android:paddingLeft")
    @JvmStatic
    fun setPaddingLeft(view: View, oldPadding: Int, newPadding: Int) {
        if (oldPadding != newPadding) {
            view.setPadding(newPadding, view.paddingTop, view.paddingRight, view.paddingBottom)
        }
    }

    @BindingAdapter("android:onLayoutChange")
    @JvmStatic
    fun setOnLayoutChangeListener(
        view: View,
        oldValue: View.OnLayoutChangeListener?,
        newValue: View.OnLayoutChangeListener?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (oldValue != null) {
                view.removeOnLayoutChangeListener(oldValue)
            }
            if (newValue != null) {
                view.addOnLayoutChangeListener(newValue)
            }
        }
    }
}