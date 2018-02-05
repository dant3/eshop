package com.github.dant3.eshop.service

import android.content.Context
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.GenericLoaderFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.github.dant3.eshop.model.Image
import java.io.InputStream

class ItemImageLoader(private val urlLoader: ModelLoader<GlideUrl, InputStream>) : ModelLoader<Image, InputStream> {
    override fun getResourceFetcher(model: Image, width: Int, height: Int): DataFetcher<InputStream> {
        val url = GlideUrl(model.url)
        return urlLoader.getResourceFetcher(url, width, height)
    }

    companion object {
        val factory: ModelLoaderFactory<Image, InputStream> =
                object : ModelLoaderFactory<Image, InputStream> {
                    override fun build(context: Context?, factories: GenericLoaderFactory): ModelLoader<Image, InputStream> {
                        return ItemImageLoader(factories.buildModelLoader(GlideUrl::class.java, InputStream::class.java))
                    }

                    override fun teardown() {}
                }
    }
}