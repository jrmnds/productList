package com.jrmnds.productlist.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jrmnds.productlist.R

object GlideHelper {

    fun loadImage(context: Context, uri: String?, imageView: ImageView){
        Glide.with(context).load(uri).into(imageView).waitForLayout().onLoadFailed(
            context.getDrawable(R.drawable.ic_baseline_image)
        )
    }

}