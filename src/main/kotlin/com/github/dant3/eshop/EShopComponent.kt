package com.github.dant3.eshop

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.dant3.eshop.api.ShoppingApi
import com.github.dant3.eshop.api.impl.ShoppingApiStub
import com.github.dant3.eshop.api.impl.json.EShopJacksonModule
import okhttp3.OkHttpClient


// normally we would use Dagger to implement this
// but what would be overkill here, for this simple application,
// especially for compile times due to codegen messing with incremental builds
interface EShopComponent {
    val api: ShoppingApi

    companion object {
        fun default() = object : EShopComponent {
            private val httpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()

            override val api: ShoppingApi = ShoppingApi.logged(ShoppingApiStub(objectMapper)) //ShoppingThroughEbayApi(httpClient, objectMapper)
        }

        val objectMapper: ObjectMapper = ObjectMapper()
                .registerKotlinModule()
                .registerModule(EShopJacksonModule())
    }
}