package com.github.dant3.eshop

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.bumptech.glide.Glide
import com.facebook.stetho.Stetho
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder
import com.github.dant3.eshop.service.ItemImageLoader
import com.github.dant3.eshop.service.registerLoaderFactory

class EShopApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        _applicationInstance = this
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this).enableWebKitInspector {
                Stetho.DefaultInspectorModulesBuilder(this).runtimeRepl(
                        JsRuntimeReplFactoryBuilder(this).apply {
                            // TODO: add customs to JS debugging console
                        }.build()
                ).finish()
            }.build())
        }

        Glide.get(applicationContext)
                .registerLoaderFactory(ItemImageLoader.factory)
    }

    companion object {
        private lateinit var _applicationInstance: EShopApplication

        val eShopComponent: EShopComponent by lazy {
            EShopComponent.default()
        }
    }
}