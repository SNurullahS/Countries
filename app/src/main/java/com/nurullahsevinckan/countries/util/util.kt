package com.nurullahsevinckan.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nurullahsevinckan.countries.R

fun ImageView.downloadImageFromUrl(url : String?,progressDrawable : CircularProgressDrawable){
    // When images can not show on context screen, this is shown
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius= 40f
        start()
    }
}