package com.example.movies.utils.imageloader

import android.widget.ImageView

interface AppImageLoader {

    fun loadInto(url: String, container: ImageView)
}