package com.github.dant3.eshop.api.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.dant3.eshop.api.Page
import com.github.dant3.eshop.api.ShoppingApi
import com.github.dant3.eshop.model.ItemSummary
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import java.io.IOException

class ShoppingThroughEbayApi(private val httpClient: OkHttpClient,
                             private val mapper: ObjectMapper): ShoppingApi {
    private val log = AnkoLogger<ShoppingApi>()

    suspend override fun findItems(searchQuery: String, page: Int): Page<ItemSummary> {
        require(page > 0) { "page must be positive number" }

        val pageSize = 20
        val offset = (page - 1) * pageSize
        val request = Request.Builder()
                .url("https://api.ebay.com/buy/browse/v1/item_summary/search?q=$searchQuery&limit=$pageSize&offset=$offset")
                .get()
                .build()

        log.info { "Request: ${request.url()} started" }
        val response = httpClient.newCall(request).executeAsync()
        log.info { "Request: ${request.url()} done, response: (${response.code()})" }
        if (response.isSuccessful) {
            val json = response.body()?.byteStream()
            try {
                // todo: better separation of parsing & http calls
                val apiResponse =  mapper.readValue<ItemsResponse>(json, ItemsResponse::class.java).apply {
                    log.info { "Result: ${this}" }
                }
                return Page(page, apiResponse.itemCount / apiResponse.limit, apiResponse.items)
            } catch (ex: Exception) {
                log.error("Failed to parse response", ex)
                throw ex
            }
        } else {
            throw IOException("Unexpected response code: ${response.code()}, message: ${response.message()}")
        }
    }
}