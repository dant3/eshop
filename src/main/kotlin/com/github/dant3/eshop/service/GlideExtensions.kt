package com.github.dant3.eshop.service

import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.ModelLoaderFactory


inline fun <reified T, reified S> Glide.registerLoaderFactory(modelLoader: ModelLoaderFactory<T, S>): Glide = apply {
    register(T::class.java, S::class.java, modelLoader)
}