package com.github.dant3.eshop.api

import com.github.dant3.eshop.model.ItemSummary
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.warn

interface ShoppingApi {
    suspend fun getFeaturedItems(page: Int = 1): Page<ItemSummary> = findItems("", page)
    suspend fun findItems(searchQuery: String, page: Int = 1): Page<ItemSummary>

    companion object {
        fun logged(shoppingApi: ShoppingApi): ShoppingApi = object: ShoppingApi {
            private val log = AnkoLogger<ShoppingApi>()


            suspend override fun findItems(searchQuery: String, page: Int): Page<ItemSummary> {
                log.warn { "findItems(query = $searchQuery, page = $page)" }
                try {
                    return shoppingApi.findItems(searchQuery, page).also {
                        log.warn { "Got ${it.items.size} items" }
                    }
                } catch (ex: Exception) {
                    log.error { "Failure: $ex" }
                    throw ex
                }
            }
        }
    }
}