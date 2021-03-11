package com.example.realworldconduitkotlin.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String, isCircular: Boolean = false) {
    //checking if image is to be circle cropped
    if(isCircular) {
        Glide.with(this).load(uri).circleCrop().into(this)
    }else {
        Glide.with(this).load(uri).into(this)
    }
}