package com.example.coroutinesdemo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun<T> ImageView.loadImage(resourseId:T){
    Glide.with(context)
        .load(resourseId)
        .into(this)
}